package com.yaseminuctas.betbullcase

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaseminuctas.betbullcase.data.model.MockData
import com.yaseminuctas.betbullcase.data.network.ApiClient
import com.yaseminuctas.betbullcase.data.network.Datum
import com.yaseminuctas.betbullcase.util.CommandTypes
import com.yaseminuctas.betbullcase.util.Const
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okio.ByteString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel(), WebSocketInterface {

    var firstSideInt: Int = 0
    lateinit var secondSideStr: String

    val liveData: MutableLiveData<MockData> = MutableLiveData()
    private val _datumList = MutableLiveData<List<Datum>>()
    var datumList = MutableLiveData<List<Datum>>()
        get() = _datumList

    private val _mainTitle = MutableLiveData<String>()
    val mainTitle: LiveData<String>
        get() = _mainTitle

    private val _newText = MutableLiveData<String>()
    val newText: LiveData<String>
        get() = _newText

    val dataList = ArrayList<Datum>()
    val socketListener: EchoWebSocketListener = EchoWebSocketListener(this)
    val client: OkHttpClient = OkHttpClient()
    var wsIsOpened: Boolean = false
    lateinit var webSocket: WebSocket
    val text = ObservableField<String?>()
    var data = String()

    //editText Watcher
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //nothing
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //nothing
        }

        override fun afterTextChanged(s: Editable?) {
            data = s.toString()
        }
    }

    fun start() {
        if (!wsIsOpened) {
            val request = Request.Builder().url("wss://echo.websocket.org").build()
            webSocket = client.newWebSocket(request, socketListener)
            client.dispatcher().executorService().shutdown()


        }
    }

    fun save() {
        if (data.isEmpty() == false) {
            webSocket.send(data)
        }
    }


    fun getData() {
        ApiClient.getApiService(null).getData().enqueue(object : Callback<MockData> {
            override fun onFailure(call: Call<MockData>, t: Throwable) {
                liveData.postValue(null)
            }
            override fun onResponse(call: Call<MockData>, response: Response<MockData>) {
                liveData.postValue(response.body())

                for (i in response.body()?.data!!) {
                    dataList.add(i)
                    _datumList.value = dataList
                }
            }
        })
    }


    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        wsIsOpened = true
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        wsIsOpened = false
        start()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {

    }

    override fun setToolbarTitle(title: String) {
        _mainTitle.postValue(title)
    }

    override fun getText(text: String) {
        _newText.postValue(text)
        val commandType = decideCommandType(text)
        actionCommands(commandType)
    }

    fun actionCommands(command: CommandTypes) {
        when(command){
            CommandTypes.LOGIN -> setToolbarTitle(Const.USER_NAME)
            CommandTypes.LOGOUT -> setToolbarTitle(Const.LOGOUT)
            CommandTypes.LIST -> changeList(firstSideInt, secondSideStr)
        }
    }

    fun decideCommandType(text: String) : CommandTypes {
        if(text.equals("LOGOUT")) {
            return CommandTypes.LOGOUT
        }
        if(text.equals("LOGIN")) {
            return CommandTypes.LOGIN
        }
        if(isListTypeCommand(text)) {
            return CommandTypes.LIST
        }
        return CommandTypes.NOCOMMAND
    }

    fun isListTypeCommand(text: String) : Boolean {
        if (text != null) {
            val splittedArray = text.split("-").toTypedArray()

            if (splittedArray.size > 1) {
                var firstSide = splittedArray[0]
                var secondSide = ""
                var i = 0
                for (element in splittedArray) {
                    if (i > 0) {
                        if (secondSide.isEmpty()) {
                            secondSide += element
                        } else {
                            secondSide += "-$element"
                        }
                    }
                    i += 1
                }
                val firstSideId = firstSide.toIntOrNull()
                firstSideId?.let {
                    firstSideInt = it
                    secondSideStr = secondSide
                    return true
                }
            }

        }
        return false
    }

    fun changeList(id: Int, name: String) {
        var i = 0
        for (element in dataList) {
            if (element.id == id) {
                dataList.get(i).name = name
                _datumList.postValue(dataList)
            }
            i += 1
        }
    }
}
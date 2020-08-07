package com.yaseminuctas.betbullcase.ui

import com.yaseminuctas.betbullcase.util.WebSocketInterface
import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString
import okhttp3.WebSocketListener



class EchoWebSocketListener(private val webSocketInterface: WebSocketInterface) : WebSocketListener() {



    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)

    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        webSocketInterface.getText(text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)

    }



    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }
}
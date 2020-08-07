package com.yaseminuctas.betbullcase

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaseminuctas.betbullcase.data.network.Datum
import com.yaseminuctas.betbullcase.data.repositories.MainViewModelFactory
import com.yaseminuctas.betbullcase.databinding.ActivityMainBinding
import com.yaseminuctas.betbullcase.ui.DatumAdapter
import com.yaseminuctas.betbullcase.util.BaseActivity
import com.yaseminuctas.betbullcase.util.Const
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: DatumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    override fun onResume() {
        super.onResume()

        viewModel.start()
    }

    private fun initDataBinding() {
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )

        toolbar.title = Const.USER_NAME
        setSupportActionBar(binding.toolbar)


        factory =
            MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.getData()
        layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recycler_view.layoutManager = layoutManager
        adapter = DatumAdapter(ArrayList<Datum>())
        recycler_view.adapter = adapter

        viewModel.datumList.observe(this, Observer { data ->
            adapter.dataList = data
            adapter.notifyDataSetChanged()
        })
        viewModel.mainTitle.observe(this, Observer {
            if (it != null)
                toolbar.title = it
        })
    }
}


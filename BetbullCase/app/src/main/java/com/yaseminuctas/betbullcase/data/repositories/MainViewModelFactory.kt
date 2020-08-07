package com.yaseminuctas.betbullcase.data.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yaseminuctas.betbullcase.ui.MainViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory() : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }

}
package com.example.dzgridlayouttablelayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {
    lateinit var inputTV:String
    lateinit var resultTV:String
    val corentActiv:MutableLiveData <String> by lazy { MutableLiveData<String>() }

}
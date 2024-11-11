package com.example.dzgridlayouttablelayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {
    lateinit var inputTV:String
    lateinit var resultTV:String
    val corentInputTV:MutableLiveData <String> by lazy { MutableLiveData<String>() }
    val corentResult:MutableLiveData<String> by lazy { MutableLiveData<String>() }
}
package com.example.dzgridlayouttablelayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {

    val result:MutableLiveData <String> by lazy { MutableLiveData<String>() }

    fun result(stroka: String) {
        var prev: String = ""
        var next: String = ""
        val size = stroka.length
        var operand: String = ""

        val lastElement: Char = stroka[stroka.length - 1]

        if (lastElement == '+' ||
            lastElement == '-' ||
            lastElement == '*' ||
            lastElement == '/'
        ) {
            result.value = "Не хватает данных"
        }

        else if (stroka.length > 2) {
            for (i in stroka.indices) {
                if (stroka[i] == '+' ||
                    stroka[i] == '-' ||
                    stroka[i] == '/' ||
                    stroka[i] == '*'
                ) {
                    operand = stroka[i].toString()
                    for (i in 0..i - 1) {
                        prev += stroka[i]
                    }
                    for (i in i + 1..size - 1) {
                        next += stroka[i]
                    }
                }
            }
        }

        if (prev != "" && next != "" && operand != "") {
            when (operand) {
                "+" -> {
                    val res = prev.toDouble() + next.toDouble()
                    result.value = res.toString()
                }

                "-" -> {
                    val res = prev.toDouble() - next.toDouble()
                    result.value = res.toString()
                }

                "*" -> {
                    val res = prev.toDouble() * next.toDouble()
                    result.value = res.toString()
                }

                "/" -> {
                    if (prev != "0" && next != "0") {
                        val res = prev.toDouble() / next.toDouble()
                        result.value = res.toString()
                    } else {
                        result.value = "Делить на 0 нельзя!"
                    }
                }
            }
        } else {
            result.value = "Данные не введены"
        }
    }
}
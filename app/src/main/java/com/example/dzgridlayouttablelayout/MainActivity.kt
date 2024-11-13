package com.example.dzgridlayouttablelayout

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: Toolbar

    lateinit var countViewModel: CountViewModel

    private lateinit var inputTV: TextView
    private lateinit var resultTV: TextView

    private lateinit var zeroBTN: Button
    private lateinit var oneBTN: Button
    private lateinit var twoBTN: Button
    private lateinit var threeBTN: Button
    private lateinit var fourBTN: Button
    private lateinit var fiveBTN: Button
    private lateinit var sixBTN: Button
    private lateinit var sevenBTN: Button
    private lateinit var eightBTN: Button
    private lateinit var nineBTN: Button
    private lateinit var sumBTN: Button
    private lateinit var divBTN: Button
    private lateinit var multBTN: Button
    private lateinit var difBTN: Button
    private lateinit var resultBTN: Button
    private lateinit var resetBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        countViewModel = ViewModelProvider(this)[CountViewModel::class.java]

        countViewModel.result.observe(this, Observer {
            resultTV.text = it
        })

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = " Калькулятор"
        toolbarMain.subtitle = "  Версия 2. Главная страница"
        toolbarMain.setLogo(R.drawable.calculator)

        inputTV = findViewById(R.id.inputTV)
        resultTV = findViewById(R.id.resultTV)

        extracted()
        extractedClick()
    }

    override fun onClick(v: View) {
        if (inputTV.text.isEmpty()) { return }
        when (v.id) {
            R.id.zeroBTN -> addValueInInputTV("0")
            R.id.oneBTN -> addValueInInputTV("1")
            R.id.twoBTN -> addValueInInputTV("2")
            R.id.threeBTN -> addValueInInputTV("3")
            R.id.fourBTN -> addValueInInputTV("4")
            R.id.fiveBTN -> addValueInInputTV("5")
            R.id.sixBTN -> addValueInInputTV("6")
            R.id.sevenBTN -> addValueInInputTV("7")
            R.id.eightBTN -> addValueInInputTV("8")
            R.id.nineBTN -> addValueInInputTV("9")
            R.id.sumBTN -> addOperandInInputTV("+")
            R.id.difBTN -> addOperandInInputTV("-")
            R.id.multBTN -> addOperandInInputTV("*")
            R.id.divBTN -> addOperandInInputTV("/")
            R.id.resultBTN -> countViewModel.result(inputTV.text.toString())
            R.id.resetBTN -> {
                inputTV.text = "O"
                resultTV.text = "Результат"
            }
            else -> {
                inputTV.text = "O"
                resultTV.text = "Результат"
            }
        }
    }

    fun addValueInInputTV(str: String) {
        if (inputTV.text.toString() == "O") {
            inputTV.text = str
        } else {
            inputTV.text = (buildString {
                append(inputTV.text.toString())
                append(str)
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run{putString("key", inputTV.text.toString())}
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputTV.text=savedInstanceState?.getString("key")
    }

    private fun extractedClick() {
        zeroBTN.setOnClickListener(this)
        oneBTN.setOnClickListener(this)
        twoBTN.setOnClickListener(this)
        threeBTN.setOnClickListener(this)
        fourBTN.setOnClickListener(this)
        fiveBTN.setOnClickListener(this)
        sixBTN.setOnClickListener(this)
        sevenBTN.setOnClickListener(this)
        eightBTN.setOnClickListener(this)
        nineBTN.setOnClickListener(this)
        sumBTN.setOnClickListener(this)
        divBTN.setOnClickListener(this)
        multBTN.setOnClickListener(this)
        difBTN.setOnClickListener(this)
        resultBTN.setOnClickListener(this)
        resetBTN.setOnClickListener(this)
    }

    private fun extracted() {
        zeroBTN = findViewById(R.id.zeroBTN)
        oneBTN = findViewById(R.id.oneBTN)
        twoBTN = findViewById(R.id.twoBTN)
        threeBTN = findViewById(R.id.threeBTN)
        fourBTN = findViewById(R.id.fourBTN)
        fiveBTN = findViewById(R.id.fiveBTN)
        sixBTN = findViewById(R.id.sixBTN)
        sevenBTN = findViewById(R.id.sevenBTN)
        eightBTN = findViewById(R.id.eightBTN)
        nineBTN = findViewById(R.id.nineBTN)

        sumBTN = findViewById(R.id.sumBTN)
        divBTN = findViewById(R.id.divBTN)
        multBTN = findViewById(R.id.multBTN)
        difBTN = findViewById(R.id.difBTN)

        resultBTN = findViewById(R.id.resultBTN)
        resetBTN = findViewById(R.id.resetBTN)
    }

    fun addOperandInInputTV(operand: String) {
        val expression: String = inputTV.text.toString()
        val lastElement: Char = expression[expression.length - 1]
        val size = expression.length
        var searchOperand: Char = 'a'

        for (i in expression) {
            if (i == '+' || i == '-' || i == '*' || i == '/') {
                searchOperand = i
            }
        }

        if (expression == "O") { //если не введён ни один символ, то операнд не вводится
            inputTV.text = "O"
        }

        else if (lastElement == '+' || lastElement == '-' ||
            lastElement == '*' || lastElement == '/') { //если последний элемент является операндом, то заменяем его на новый операнд
            var expressionNew: String = ""
            for (i in 0 .. expression.length - 2) {
                expressionNew += expression[i]
            }
            inputTV.text = expressionNew + operand
        }

        else if (searchOperand != 'a') { //если операнд уже есть в выражении то второй не водится
            inputTV.text = expression
        }

        else { //во всех остальных случаях операнд добавляется к выражению
            inputTV.text = expression + operand
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Создан 11.11.2024",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
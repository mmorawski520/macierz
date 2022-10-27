package com.example.macierz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var i:Int = 0;
    var j:Int = 0;
    var arrSize = 100;
    val array = Array(arrSize, {IntArray(arrSize)});

    lateinit var btnApply:Button;
    lateinit var editTextValue:EditText;

    lateinit var editTextI:EditText;
    lateinit var btnIForward:Button;
    lateinit var btnIBack:Button;

    lateinit var editTextJ:EditText;
    lateinit var btnJForward:Button;
    lateinit var btnJBack:Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnApply = findViewById(R.id.btnApply);
        editTextValue = findViewById(R.id.editTextValue);

        editTextI = findViewById(R.id.editTextI);
        btnIForward = findViewById(R.id.btnIForward);
        btnIBack = findViewById(R.id.btnIBack);

        editTextJ = findViewById(R.id.editTextJ);
        btnJForward = findViewById(R.id.btnJForward);
        btnJBack = findViewById(R.id.btnJBack);
    }

    fun loadValue(){

    }
}
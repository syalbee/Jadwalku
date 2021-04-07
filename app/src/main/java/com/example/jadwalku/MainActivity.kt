package com.example.jadwalku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.jadwalku.helper.sharedPref
import com.example.jadwalku.views.Active
import com.example.jadwalku.views.Home
import com.example.jadwalku.views.signUp

class MainActivity : AppCompatActivity() {

    private lateinit var s:sharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)

        s = sharedPref(this)

        if(s.getStatusLogin()){
            val intent = Intent(this, Home::class.java)
            startActivity(intent);
        }

        btnStart.setOnClickListener {
            val intent = Intent(this, signUp::class.java)
            startActivity(intent);
        }
    }
}
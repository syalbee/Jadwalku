package com.example.jadwalku.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jadwalku.MainActivity
import com.example.jadwalku.R
import com.example.jadwalku.helper.sharedPref

class Home : AppCompatActivity() {

    lateinit var s: sharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        s = sharedPref(this)

        val btnOngoing = findViewById<Button>(R.id.btOngoing)
        val btnActive = findViewById<Button>(R.id.btActive)
        val btnDone = findViewById<Button>(R.id.btDone)
        val btnLogout = findViewById<Button>(R.id.btLogout)

        btnActive.setOnClickListener {
            val intent = Intent(this, Active::class.java)
            startActivity(intent);
        }

        btnDone.setOnClickListener {
            val intent = Intent(this, Done::class.java)
            startActivity(intent);
        }

        btnLogout.setOnClickListener {
            s.setStatusLogin(false)
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }



    }


}
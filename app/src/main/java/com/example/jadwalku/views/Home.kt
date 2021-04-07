package com.example.jadwalku.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jadwalku.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnOngoing = findViewById<Button>(R.id.btOngoing)
        val btnActive = findViewById<Button>(R.id.btActive)
        val btnDone = findViewById<Button>(R.id.btDone)

        btnActive.setOnClickListener {
            val intent = Intent(this, Active::class.java)
            startActivity(intent);
        }

        btnDone.setOnClickListener {
            val intent = Intent(this, Done::class.java)
            startActivity(intent);
        }



    }


}
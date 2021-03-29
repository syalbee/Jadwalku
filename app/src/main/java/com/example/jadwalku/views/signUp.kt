package com.example.jadwalku.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.jadwalku.R


class signUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val btnLogin = findViewById<Button>(R.id.btnLogin)


        btnSignup.setOnClickListener {
            register()
        }


    }

    fun register(){
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPasword = findViewById<EditText>(R.id.etPassword)

        if(etNama.text.isEmpty()) {
            etNama.error = "isi Kolom nama !"
            etNama.requestFocus()
            return
        } else if(etEmail.text.isEmpty()) {
            etEmail.error = "isi Kolom email !"
            etEmail.requestFocus()
            return
        } else if(etPasword.text.isEmpty()) {
            etPasword.error = "isi Kolom password !"
            etPasword.requestFocus()
            return
        }
    }
}
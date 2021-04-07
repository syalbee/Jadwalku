package com.example.jadwalku.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jadwalku.R
import com.example.jadwalku.app.ApiConfig
import com.example.jadwalku.helper.sharedPref
import com.example.jadwalku.model.ResponModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class signUp : AppCompatActivity() {

    lateinit var s: sharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        s= sharedPref(this)

        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val btnLogin = findViewById<Button>(R.id.btnLogin)


        btnSignup.setOnClickListener {
            register()
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent);
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

        ApiConfig.instanceRetrofit.register(etNama.text.toString(), etEmail.text.toString(), etPasword.text.toString())
                .enqueue(object : Callback<ResponModel>{

                    override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                        Toast.makeText(this@signUp, "gagal", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<ResponModel>,
                        response: Response<ResponModel>
                    ) {
                        val respon = response.body()!!

                        if(respon.success == 1){
                            s.setStatusLogin(true)
                            val intent = Intent(this@signUp, Home::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this@signUp, "Welcome " + respon.user.name, Toast.LENGTH_SHORT).show()

                        } else{
                            Toast.makeText(this@signUp, "Error " + respon.message, Toast.LENGTH_SHORT).show()
                        }

                    }

                })
    }
}
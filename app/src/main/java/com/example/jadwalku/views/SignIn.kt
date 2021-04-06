package com.example.jadwalku.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jadwalku.R
import com.example.jadwalku.app.ApiConfig
import com.example.jadwalku.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnLogin = findViewById<Button>(R.id.btnSignin)

        btnLogin.setOnClickListener {
            login()
        }
    }


    fun login(){
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPasword = findViewById<EditText>(R.id.etPassword)

        if(etEmail.text.isEmpty()) {
            etEmail.error = "isi Kolom email !"
            etEmail.requestFocus()
            return

        } else if(etPasword.text.isEmpty()) {
            etPasword.error = "isi Kolom password !"
            etPasword.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.login(etEmail.text.toString(), etPasword.text.toString())
            .enqueue(object : Callback<ResponModel> {

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    Toast.makeText(this@SignIn, "gagal", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponModel>,
                    response: Response<ResponModel>
                ) {
                    val respon = response.body()!!

                    if(respon.success == 1){
                        Toast.makeText(this@SignIn, "Welcome " + respon.user.name, Toast.LENGTH_SHORT).show()

                    } else{
                        Toast.makeText(this@SignIn, "Error " + respon.message, Toast.LENGTH_SHORT).show()
                    }

                }

            })
    }
}
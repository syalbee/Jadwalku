package com.example.jadwalku.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
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
        val pbLogin = findViewById<ProgressBar>(R.id.pbLogin)

        if(etEmail.text.isEmpty()) {
            etEmail.error = "isi Kolom email !"
            etEmail.requestFocus()
            return

        } else if(etPasword.text.isEmpty()) {
            etPasword.error = "isi Kolom password !"
            etPasword.requestFocus()
            return
        }

        pbLogin.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(etEmail.text.toString(), etPasword.text.toString())
            .enqueue(object : Callback<ResponModel> {

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    pbLogin.visibility = View.GONE
                    Toast.makeText(this@SignIn, "gagal" + t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponModel>,
                    response: Response<ResponModel>
                ) {
                    val respon = response.body()!!

                    pbLogin.visibility = View.GONE

                    if(respon.success == 1){

                        val intent = Intent(this@SignIn, Home::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@SignIn, "Welcome " + respon.user.name, Toast.LENGTH_SHORT).show()

                    } else{
                        Toast.makeText(this@SignIn, "Error " + respon.message, Toast.LENGTH_SHORT).show()
                    }

                }

            })
    }
}
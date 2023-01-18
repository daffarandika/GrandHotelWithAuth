package com.example.grandhotelwithauth

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.grandhotelwithauth.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animationDrawable = binding.clLogin.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(250)
        animationDrawable.setExitFadeDuration(500)
        animationDrawable.start()
        binding.ibShowPassword.setOnClickListener{
            Toast.makeText(this, "yo", Toast.LENGTH_SHORT).show()
        }
        binding.btnLogin.setOnClickListener{
            val name = binding.etLoginName.text.toString()
            val password = binding.etLoginPassword.text.toString()
            login(name, password)
        }
    }
    fun login(name: String, password: String) = runBlocking{
        launch(Dispatchers.IO) {
            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("password", password)
            val conn = URL(CONSTS.url+"/api/auth/login").openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-type", "application/json")
            conn.doInput = true
            conn.doOutput = true

            val outputStreamWriter = OutputStreamWriter(conn.outputStream)
            outputStreamWriter.write(jsonObject.toString())
            outputStreamWriter.flush()
            if (conn.responseCode == 200) {
                CONSTS.token = conn.inputStream.bufferedReader().readText()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                runOnUiThread{
                    Toast.makeText(applicationContext, "Failed to log in please check your name and password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
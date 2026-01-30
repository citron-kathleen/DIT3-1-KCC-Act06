package com.example.apiconnectapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFetch = findViewById<Button>(R.id.btnFetch)
        val tvResults = findViewById<TextView>(R.id.tvResults)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        btnFetch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            btnFetch.isEnabled = false
            tvResults.text = "Fetching real-time data..."

            lifecycleScope.launch {
                try {
                    val posts = apiService.getPosts()
                    val resultText = StringBuilder()

                    for (post in posts.take(10)) {
                        resultText.append("ID: ${post.id}\n")
                        resultText.append("TITLE: ${post.title.uppercase()}\n")
                        resultText.append("CONTENT: ${post.body}\n")
                        resultText.append("_________________________________\n\n")
                    }
                    tvResults.text = resultText.toString()
                } catch (e: Exception) {
                    // Connectivity check requirement
                    Toast.makeText(this@MainActivity, "Connection Error!", Toast.LENGTH_SHORT).show()
                    tvResults.text = "Error: Check your internet connection."
                } finally {
                    progressBar.visibility = View.GONE
                    btnFetch.isEnabled = true
                }
            }
        }
    }
}
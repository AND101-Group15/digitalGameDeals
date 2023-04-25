package com.example.digitalgamedeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val titleView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
           getTitle(titleView)
        }

    }
}

private fun getTitle(titleView: TextView) {
    val client = AsyncHttpClient()
    client["https://www.cheapshark.com/api/1.0/games?id=612", object : JsonHttpResponseHandler() {
        override fun onFailure(
            statusCode: Int,
            headers: Headers?,
            response: String?,
            throwable: Throwable?
        ) {
            Log.d("Title Fail", "error fetching!")
        }

        override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
            Log.d("Title Success", "response successful!")
            json?.jsonObject?.getJSONObject("info")?.getString("title")
        }

    }]
}
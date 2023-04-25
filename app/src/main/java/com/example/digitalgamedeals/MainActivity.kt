package com.example.digitalgamedeals

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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
           val title = json?.jsonObject?.getJSONObject("cheapestPriceEver")?.getString("price")

            titleView.text = title
        }

    }]
}
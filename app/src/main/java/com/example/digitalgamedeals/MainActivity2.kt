package com.example.digitalgamedeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val searchTitle =findViewById<TextView>(R.id.game_name)

        val bundle : Bundle?= intent.extras
        val search = bundle!!.getString("game_title")
        val gameId = bundle.getInt("gameID")

        searchTitle.text = search

        if (search != null) {
            getDealInfo(search)
        }
    }
}

private fun getDealInfo(search: String) {
    val client = AsyncHttpClient()
    client["https://www.cheapshark.com/api/1.0/deals?title=$search", object :
        JsonHttpResponseHandler() {
        override fun onFailure(
            statusCode: Int,
            headers: Headers?,
            response: String?,
            throwable: Throwable?
        ) {
            Log.d("Deal Fail", "error fetching!")
        }


        override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
            Log.d("Deal Success", "response successful!")
            Log.d("Deal Success", json.toString())

            var jsonArray = json!!.jsonArray

            for (i in 0 until json.jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                val title  = jsonObject.getString("title")
                dealList.add(title)
                val salePrice  = jsonObject.getString("salePrice")
                dealList.add(salePrice)
                val normalPrice  = jsonObject.getString("normalPrice")
                dealList.add(normalPrice)
                val isOnSale  = jsonObject.getString("isOnSale")
                dealList.add(isOnSale)
                val thumb = jsonObject.getString("thumb")
                dealList.add(thumb)
                Glide.with(this@MainActivity)
                    .load(thumb)
                    .fitCenter()
                    .into()
            }

        }

    }]
}
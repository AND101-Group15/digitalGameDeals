package com.example.digitalgamedeals

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    private lateinit var gameTitleList: MutableList<String>
    private lateinit var gameNormalPriceList: MutableList<String>
    private lateinit var gameSalePriceList: MutableList<String>
    private lateinit var gameStoreList: MutableList<String>
    private lateinit var gameIsOnSale: MutableList<String>
    private lateinit var gameThumbnailList: MutableList<String>
    private lateinit var gameRatingNumList: MutableList<String>
    private lateinit var gameRatingTextList: MutableList<String>
    private lateinit var rvDeals: RecyclerView
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val searchTitle = findViewById<TextView>(R.id.game_name)

        gameTitleList = mutableListOf()
        gameNormalPriceList = mutableListOf()
        gameSalePriceList = mutableListOf()
        gameStoreList = mutableListOf()
        gameIsOnSale = mutableListOf()
        gameThumbnailList = mutableListOf()
        gameRatingNumList = mutableListOf()
        gameRatingTextList = mutableListOf()



        val bundle: Bundle? = intent.extras
        val search = bundle!!.getString("game_title")
        if (search != null) {
            Log.d("MainActivity2", search)
        }

        searchTitle.text = search

        if (search != null) {
            getDealInfo(search)
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


            @SuppressLint("ResourceAsColor")
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JsonHttpResponseHandler.JSON) {
                Log.d("Deal Success", "response successful!")
                Log.d("Deal Success", json.toString())

                val jsonArray = json.jsonArray

                for (i in 0 until json.jsonArray.length()) {
                    val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                    val salePrice = jsonObject.getString("salePrice")
                    gameSalePriceList.add(salePrice)
                    val normalPrice = jsonObject.getString("normalPrice")
                    gameNormalPriceList.add(normalPrice)
                    val storeID = jsonObject.getString("storeID")
                    gameStoreList.add(storeID)
                    val isOnSale = jsonObject.getString("isOnSale")
                    gameIsOnSale.add(isOnSale)
                    val thumb = jsonObject.getString("thumb")
                    gameThumbnailList.add(thumb)
                    val ratingNum = jsonObject.getString("steamRatingPercent")
                    gameRatingNumList.add(ratingNum)
                    val ratingText = jsonObject.getString("steamRatingText")
                    gameRatingTextList.add(ratingText)


                    rvDeals = findViewById(R.id.deals_list)
                    val adapter = DealsAdapter(gameNormalPriceList, gameSalePriceList,gameStoreList,gameThumbnailList, gameRatingNumList, gameRatingTextList)
                    rvDeals.adapter = adapter
                    rvDeals.layoutManager = LinearLayoutManager(this@MainActivity2)

                    val mDividerItemDecoration = DividerItemDecoration(this@MainActivity2, LinearLayoutManager.VERTICAL)
                    mDividerItemDecoration.setDrawable(ColorDrawable(R.color.black))
                    rvDeals.addItemDecoration(mDividerItemDecoration)


                }

            }

        }]
    }
}
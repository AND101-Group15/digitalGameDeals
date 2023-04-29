package com.example.digitalgamedeals

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var gameTitleList: MutableList<String>
    private lateinit var gamePriceList: MutableList<String>
    private lateinit var gameIDList: MutableList<String>
    private lateinit var gameImageList: MutableList<String>
    private lateinit var rvGame: RecyclerView
    private lateinit var adapter: GameAdapter


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameTitleList = mutableListOf()
        gamePriceList = mutableListOf()
        gameIDList = mutableListOf()
        gameImageList = mutableListOf()
        // Set Recycler to invisible until data is passed
        rvGame = findViewById(R.id.game_list)
        rvGame.visibility = View.GONE


        val searchBar = findViewById<EditText>(R.id.editTextGameSearch)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val search = searchBar.text.toString()
            getGameInfo(search)


            val adapter = GameAdapter(gameTitleList, gamePriceList, gameIDList, gameImageList)
            rvGame.adapter = adapter
            adapter.setOnItemClickListener(object : GameAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    // This allows us to keep the name and game ID to the next activity
                    intent.putExtra("game_title", gameTitleList[position])
                    startActivity(intent)
                }

            })
            rvGame.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGame.visibility = View.VISIBLE

            val mDividerItemDecoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            mDividerItemDecoration.setDrawable(ColorDrawable(R.color.black))
            rvGame.addItemDecoration(mDividerItemDecoration)
        }
        adapter = GameAdapter(gameTitleList, gamePriceList, gameIDList, gameImageList)
        val sort = findViewById<Button>(R.id.sort)
        sort.setOnClickListener {
            val search = searchBar.text.toString()
            getGameInfo(search)

            adapter = GameAdapter(gameTitleList, gamePriceList, gameIDList, gameImageList)
            rvGame.adapter = adapter
            adapter.setOnItemClickListener(object : GameAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("game_title", gameTitleList[position])
                    intent.putExtra("gameID",gameIDList[position].toInt())
                    startActivity(intent)
                }

            })
            rvGame.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGame.visibility = View.VISIBLE

            val mDividerItemDecoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            mDividerItemDecoration.setDrawable(ColorDrawable(R.color.black))
            rvGame.addItemDecoration(mDividerItemDecoration)

            gamePriceList.sortBy { it.toDouble() }
            adapter.notifyDataSetChanged()
        }


    }

    private fun getGameInfo(searchBar: String) {
        val client = AsyncHttpClient()
        client["https://www.cheapshark.com/api/1.0/games?title=$searchBar", object :
            JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Info Fail", "error fetching!")
            }


            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d("Info Success", "response successful!")
                Log.d("Info Success", json.toString())

                val jsonArray = json!!.jsonArray

                for (i in 0 until json.jsonArray.length()) {
                    val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    val title  = jsonObject.getString("external")
                    gameTitleList.add(title)
                    val cheapestPrice  = jsonObject.getString("cheapest")
                    gamePriceList.add(cheapestPrice)
                    val gameID  = jsonObject.getString("gameID")
                    gameIDList.add(gameID)
                    val gameImage = jsonObject.getString("thumb")
                    gameImageList.add(gameImage)
                }

            }

        }]
    }
}

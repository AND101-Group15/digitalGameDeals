package com.example.digitalgamedeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val searchTitle =findViewById<TextView>(R.id.game_name)

        val bundle : Bundle?= intent.extras
        val search = bundle!!.getString("game_title")
        val gameId = bundle.getInt("gameID")

        searchTitle.text = search
    }
}
package com.example.digitalgamedeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class GameAdapter (private val gameTitleList: List<String>,
                   private val gamePriceList: List<String>,
                   private val gameIDList: List<String>): RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val gameTitle: TextView
        val gamePrice: TextView

        init {
            // Find our RecyclerView item's ImageView for future use

            gameTitle = view.findViewById(R.id.game_title)
            gamePrice = view.findViewById(R.id.game_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = gameTitleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.gameTitle.text = gameTitleList[position]
        holder.gamePrice.text = gamePriceList[position]
        val gameID = gameIDList[position]

        holder.gameTitle.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Game ID: $gameID", Toast.LENGTH_SHORT).show()
        }

    }
}
package com.example.digitalgamedeals


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GameAdapter (private val gameTitleList: List<String>,
                   private val gamePriceList: List<String>,
                   private val gameIDList: List<String>,
                   private val gameImageList: List<String>): RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    class ViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val gameThumbnail: ImageButton
        val gameTitle: TextView
        val gamePrice: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            gameThumbnail = view.findViewById(R.id.game_thumbnail)
            gameTitle = view.findViewById(R.id.game_title)
            gamePrice = view.findViewById(R.id.game_price)
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position : Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun getItemCount() = gameTitleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(gameImageList[position])
            .centerCrop()
            .into(holder.gameThumbnail)

        holder.gameTitle.text = gameTitleList[position]
        holder.gamePrice.text = gamePriceList[position]


    }
}
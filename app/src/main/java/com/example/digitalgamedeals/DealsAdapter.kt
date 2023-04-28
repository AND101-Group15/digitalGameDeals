package com.example.digitalgamedeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DealsAdapter(private val gameNormalPriceList: List<String>,
                    private val gameSalePriceList: List<String>,
                    private val gameIsOnSaleList: List<String>,
                    private val gameThumbnailList: List<String>,
                    private val gameRatingNumList: List<String>,
                    private val gameRatingTextList: List<String>): RecyclerView.Adapter<DealsAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val gameNormalPrice: TextView
        val gameSalePrice: TextView
        val gameIsOnSale: ImageView
        val gameThumbnail: ImageView
        val gameRatingNum: TextView
        val gameRatingText: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            gameThumbnail = view.findViewById(R.id.thumb)
            gameNormalPrice = view.findViewById(R.id.normalPrice)
            gameSalePrice = view.findViewById(R.id.salePrice)
            gameIsOnSale = view.findViewById(R.id.isOnSale)
            gameRatingNum = view.findViewById(R.id.ratingNum)
            gameRatingText = view.findViewById(R.id.ratingText)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deal_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = gameNormalPriceList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.gameNormalPrice.text = "$"+ gameNormalPriceList[position]
        holder.gameSalePrice.text = "$"+ gameSalePriceList[position]

        if (gameNormalPriceList[position] === gameSalePriceList[position]) {
            holder.gameIsOnSale.setVisibility(View.GONE)
        } else {
            holder.gameIsOnSale.setVisibility(View.VISIBLE)
        }

        Glide.with(holder.itemView)
            .load(gameThumbnailList[position])
            .centerCrop()
            .into(holder.gameThumbnail)

        holder.gameRatingNum.text = gameRatingNumList[position]
        holder.gameRatingText.text = gameRatingTextList[position]
    }
}
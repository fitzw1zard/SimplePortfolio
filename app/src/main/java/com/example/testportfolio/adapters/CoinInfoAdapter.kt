package com.example.testportfolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testportfolio.R
import com.example.testportfolio.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    private lateinit var binding: ResultProfileBinding

    var coinPriceInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        return CoinInfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_coin_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinPriceInfoList[position]
        with(holder) {
            tvSymbols.text = coin.fromSymbol + "/" + coin.toSymbol
            tvPrice.text = coin.price
            tvLastUpdate.text = coin.getFormattedTime()
        }
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivCoinLogo)
    }

    override fun getItemCount() = coinPriceInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


            val ivCoinLogo = binding.
            val tvSymbols = itemView.tvSymbols
            val tvPrice = itemView.tvPrice
            val tvLastUpdate = itemView.tvLastUpdate

    }
}

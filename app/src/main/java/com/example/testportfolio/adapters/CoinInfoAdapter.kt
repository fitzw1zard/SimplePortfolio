package com.example.testportfolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testportfolio.R
import com.example.testportfolio.databinding.ItemCoinInfoBinding
import com.example.testportfolio.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinPriceInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {

        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )

        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinPriceInfoList[position]

        with(holder) {
            tvSymbols.text = coin.fromSymbol + "/" + coin.toSymbol
            tvPrice.text = coin.price
            tvLastUpdate.text = coin.getFormattedTime()
            Picasso.get().load(coin.getFullImageUrl()).into(ivCoinLogo)
        }

    }

    override fun getItemCount() = coinPriceInfoList.size

    inner class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        fun bind(coin: CoinPriceInfo) {
//            with(binding) {
        val ivCoinLogo = binding.ivCoinLogo
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvLastUpdate = binding.tvLastUpdate
//            }
//    }
    }
}



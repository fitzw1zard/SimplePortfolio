package com.example.testportfolio.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testportfolio.R
import com.example.testportfolio.databinding.ItemCoinInfoBinding
import com.example.testportfolio.domain.entity.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoDtoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {

        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )

        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoDtoList[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        with(holder) {
            with(coin) {
            tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
            tvPrice.text = price
            tvLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
            Picasso.get().load(imageUrl).into(ivCoinLogo)
            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(this)
            }}
        }

    }

    override fun getItemCount() = coinInfoDtoList.size

    inner class CoinInfoViewHolder(binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val ivCoinLogo = binding.ivCoinLogo
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvLastUpdate = binding.tvLastUpdate

    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfoDto: CoinInfo)
    }

}




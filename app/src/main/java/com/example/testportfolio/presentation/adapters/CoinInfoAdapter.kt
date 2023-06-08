package com.example.testportfolio.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testportfolio.R
import com.example.testportfolio.databinding.ItemCoinInfoBinding
import com.example.testportfolio.domain.entity.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfo, CoinInfoAdapter.CoinInfoViewHolder>(
    object : DiffUtil.ItemCallback<CoinInfo>() {
        override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
            return oldItem.fromSymbol == newItem.fromSymbol
        }

        override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
            return oldItem == newItem
        }
    }
) {

    var onCoinClickListener: ((CoinInfo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinInfoBinding.inflate(
            inflater,
            parent,
            false
        )

        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin)
    }

    inner class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: CoinInfo) {
            with(coin) {
                with(binding) {
                    val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                    val lastUpdateTemplate =
                        context.resources.getString(R.string.last_update_template)
                    tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                    tvPrice.text = price
                    tvLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
                    Picasso.get().load(imageUrl).into(ivCoinLogo)
                    root.setOnClickListener {
                        onCoinClickListener?.invoke(coin)
                    }
                }
            }
        }
    }

}




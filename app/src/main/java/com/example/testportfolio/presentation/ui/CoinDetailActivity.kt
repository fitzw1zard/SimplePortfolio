package com.example.testportfolio.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testportfolio.R
import com.example.testportfolio.databinding.ActivityCoinDetailBinding
import com.example.testportfolio.presentation.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso


class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL).orEmpty()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
            with(binding) {
                Picasso.get().load(it.imageUrl).into(ivCoinLogo)
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                tvPriceSet.text = it.price.toString()
                tvMinSet.text = it.lowDay.toString()
                tvMaxSet.text = it.highDay.toString()
                tvLastDealSet.text = it.lastMarket
                tvLastUpdateSet.text = it.lastUpdate
            }
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
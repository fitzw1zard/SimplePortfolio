package com.example.testportfolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testportfolio.presentation.adapters.CoinInfoAdapter
import com.example.testportfolio.databinding.ActivityMainBinding
import com.example.testportfolio.data.model.CoinPriceInfo
import com.example.testportfolio.presentation.ui.CoinDetailActivity
import com.example.testportfolio.presentation.viewmodel.CoinViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                startActivity(
                    CoinDetailActivity.newIntent(
                        this@MainActivity,
                        coinPriceInfo.fromSymbol
                    )
                )
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvoCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            adapter.coinPriceInfoList = it
        }


    }

}

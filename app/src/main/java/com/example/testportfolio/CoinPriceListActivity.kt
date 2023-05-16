package com.example.testportfolio

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testportfolio.adapters.CoinInfoAdapter
import com.example.testportfolio.databinding.ActivityCoinPriceListBinding
import com.example.testportfolio.pojo.CoinPriceInfo


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("ON_CLICK_TEST", "Click: ${coinPriceInfo.fromSymbol}")
            }
        }
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvoCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
           adapter.coinPriceInfoList = it
        }



    }

}

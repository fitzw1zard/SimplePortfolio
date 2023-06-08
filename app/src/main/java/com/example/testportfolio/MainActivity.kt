package com.example.testportfolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.testportfolio.presentation.adapters.CoinInfoAdapter
import com.example.testportfolio.databinding.ActivityMainBinding
import com.example.testportfolio.domain.entity.CoinInfo
import com.example.testportfolio.presentation.ui.CoinDetailActivity
import com.example.testportfolio.presentation.viewmodel.CoinViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }
    }

    private fun observeViewModel() {

    }

    private fun setupRecyclerView() {
        coinInfoAdapter = CoinInfoAdapter(this)
        binding.rvoCoinPriceList.adapter = coinInfoAdapter
        binding.rvoCoinPriceList.itemAnimator = null
        setupClickListener()
    }


    private fun setupClickListener() {
        coinInfoAdapter.onCoinClickListener = {
            val intent = CoinDetailActivity.newIntent(this, it.fromSymbol)
            startActivity(intent)
        }
    }


}

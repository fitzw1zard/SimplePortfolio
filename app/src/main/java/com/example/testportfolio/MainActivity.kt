package com.example.testportfolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.testportfolio.presentation.adapters.CoinInfoAdapter
import com.example.testportfolio.databinding.ActivityMainBinding
import com.example.testportfolio.domain.entity.CoinInfo
import com.example.testportfolio.presentation.ui.CoinDetailActivity
import com.example.testportfolio.presentation.ui.CoinDetailFragment
import com.example.testportfolio.presentation.viewmodel.CoinViewModel
import com.example.testportfolio.presentation.viewmodel.ViewModelFactory
import java.text.DateFormatSymbols
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (application as MainApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
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
            if (isOnePaneMode()) {
                launchDetailActivity(it.fromSymbol)
            } else {
                launchDetailFragment(it.fromSymbol)
            }
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(this@MainActivity, fromSymbol)
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

}

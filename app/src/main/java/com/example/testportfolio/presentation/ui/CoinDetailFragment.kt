package com.example.testportfolio.presentation.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testportfolio.MainApp
import com.example.testportfolio.R
import com.example.testportfolio.databinding.ActivityCoinDetailBinding
import com.example.testportfolio.databinding.FragmentCoinDetailBinding
import com.example.testportfolio.presentation.viewmodel.CoinViewModel
import com.example.testportfolio.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class CoinDetailFragment : Fragment() {


    private var _binding: FragmentCoinDetailBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding == null")

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as MainApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
             super.onViewCreated(view, savedInstanceState)
        val fromSymbol = getSymbol()
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner) {
            with(binding) {
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                tvPriceSet.text = it.price.toString().take(10)
                tvMinSet.text = it.lowDay.toString().take(10)
                tvMaxSet.text = it.highDay.toString().take(10)
                tvLastDealSet.text = it.lastMarket
                tvLastUpdateSet.text = it.lastUpdate
                Picasso.get().load(it.imageUrl).into(ivCoinLogo)

            }
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSymbol() = requireArguments().getString(EXTRA_FROM_SYMBOL).orEmpty()

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newInstance(fromSymbol: String): Fragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}

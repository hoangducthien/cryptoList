package com.example.cryptolist.demo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.example.cryptolist.R
import com.example.cryptolist.currency.CurrencyListFragment
import com.example.cryptolist.currency.TYPE_ALL
import com.example.cryptolist.currency.TYPE_CRYPTO
import com.example.cryptolist.currency.TYPE_FIAT
import com.example.cryptolist.databinding.ActivityDemoLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private val viewModel by viewModels<DemoViewModel>()
    private var currentDataType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDemoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonClear.setOnClickListener {
            viewModel.clearData()
        }
        binding.buttonInsert.setOnClickListener {
            viewModel.insertData()
        }
        binding.buttonShowAll.setOnClickListener {
            switchPage(TYPE_ALL)
        }
        binding.buttonShowFiat.setOnClickListener {
            switchPage(TYPE_FIAT)
        }
        binding.buttonShowCrypto.setOnClickListener {
            switchPage(TYPE_CRYPTO)
        }
        switchPage(TYPE_ALL)
    }

    private fun switchPage(dataType: Int) {
        if (dataType == currentDataType) {
            return
        }
        currentDataType = dataType
        CurrencyListFragment.createCurrencyFragment(currentDataType).let {
            supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container, it)
                .setTransition(TRANSIT_FRAGMENT_FADE)
                .commitAllowingStateLoss()
        }
    }

}
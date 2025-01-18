package com.example.cryptolist.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptolist.R
import com.example.cryptolist.common.BaseFragment
import com.example.domain.common.safe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : BaseFragment<CurrencyListViewModel>() {

    companion object {
        private const val DATA_TYPE = "DATA_TYPE"

        fun createCurrencyFragment(dataType: Int): CurrencyListFragment {
            return CurrencyListFragment().apply {
                arguments = Bundle().apply {
                    putInt(DATA_TYPE, dataType)
                }
            }
        }
    }

    override val viewModel by viewModels<CurrencyListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataType = arguments?.getInt(DATA_TYPE).safe()
        viewModel.getCurrencyList(dataType)
        return buildComposeContentView(getTitle(dataType)) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                SearchBox(viewModel::search)
                val currencyList by viewModel.currencyListFlow.collectAsStateWithLifecycle()
                val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
                if (currencyList.isEmpty() && isSearching) {
                    EmptyStateUI()
                } else {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(currencyList) { currency ->
                            CurrencyItem(currency = currency)
                        }
                    }
                }
            }
        }
    }

    private fun getTitle(dataType: Int): String {
        return when (dataType) {
            TYPE_CRYPTO -> getString(R.string.crypto_list)
            TYPE_FIAT -> getString(R.string.fiat_list)
            else -> getString(R.string.all_list)
        }
    }
}


@Composable
fun EmptyStateUI() {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "(·_·)", fontSize = 80.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        Text(stringResource(R.string.no_results), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(stringResource(R.string.try_mco), fontSize = 18.sp, color = Color.Gray)
    }
}

@Composable
fun SearchBox(onSearchUpdate: (String) -> Unit) {
    var isActiveShow by remember {
        mutableStateOf(false)
    }
    var searchKeyword by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            val focusRequester = remember { FocusRequester() }
            TextField(
                value = searchKeyword, onValueChange = {
                    searchKeyword = it
                    onSearchUpdate(it)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Row {
                        Text(text = stringResource(R.string.search_by_name))
                    }
                },
                shape = RoundedCornerShape(16.dp),
                enabled = isActiveShow,

                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isActiveShow = true
                    }
                    .focusRequester(focusRequester),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                )
            )
            if (isActiveShow) {
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }
        AnimatedVisibility(visible = isActiveShow) {
            Text(
                text = stringResource(R.string.cancel),
                modifier = Modifier
                    .clickable {
                        isActiveShow = false
                        searchKeyword = ""
                        onSearchUpdate("")
                    }
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                color = Color.Blue
            )
        }
    }
}

@Composable
fun CurrencyItem(currency: UICurrency) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        Box(modifier = Modifier
            .size(30.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Gray,
                    cornerRadius = CornerRadius(15.dp.toPx(), 15.dp.toPx())
                )
            }) {
            Text(
                text = currency.name.substring(0, 1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 3.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(Modifier.width(10.dp))
        Text(text = currency.name, Modifier.weight(1f))
        Text(text = currency.displayCode)
        Spacer(Modifier.width(10.dp))
        if (currency.displayCode.isNotEmpty()) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}



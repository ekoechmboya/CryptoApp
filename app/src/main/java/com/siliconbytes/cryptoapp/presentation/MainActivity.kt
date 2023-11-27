package com.siliconbytes.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siliconbytes.cryptoapp.CoinApplication
import com.siliconbytes.cryptoapp.domain.use_case.get_coin.GetCoinUseCase
import com.siliconbytes.cryptoapp.domain.use_case.get_coins.GetCoinsUseCase
import com.siliconbytes.cryptoapp.presentation.coin_detail.CoinDetailScreen
import com.siliconbytes.cryptoapp.presentation.coin_detail.CoinDetailViewModel
import com.siliconbytes.cryptoapp.presentation.coin_list.CoinListViewModel
import com.siliconbytes.cryptoapp.presentation.coin_list.components.CoinListScreen
import com.siliconbytes.cryptoapp.presentation.ui.theme.CryptoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {

                val CoinListViewModel = viewModel<CoinListViewModel>(
                    factory = viewModelFactory {
                        CoinListViewModel(GetCoinsUseCase(CoinApplication.appModule.coinRepository))
                    }
                )
                val CoinDetailViewModel = viewModel<CoinDetailViewModel>(
                    factory = viewModelFactory {
                        CoinDetailViewModel(GetCoinUseCase(CoinApplication.appModule.coinRepository))
                    }
                )
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.CoinListScreen.route ){
                        composable(
                            route = Screen.CoinListScreen.route
                        ){
                            CoinListScreen(navController = navController, CoinListViewModel)
                        }
                        composable(
                            route = "coin_detail_screen/{coinId}"
                        ){
                            CoinDetailScreen( CoinDetailViewModel)
                        }
                    }
                }
            }
        }
    }
}


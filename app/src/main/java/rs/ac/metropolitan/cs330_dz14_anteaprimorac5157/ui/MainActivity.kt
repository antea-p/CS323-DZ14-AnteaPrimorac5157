package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.RetrofitHelperImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.RepositoryImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCasesImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.HomeScreen
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.theme.CS330DZ14AnteaPrimorac5157Theme
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModelImpl

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                val apiService = RetrofitHelperImpl().getApiService()
                val repository = RepositoryImpl(apiService)
                val useCases = UseCasesImpl(repository)
                @Suppress("UNCHECKED_CAST")
                return AppViewModelImpl(useCases) as T
            }
        })[AppViewModelImpl::class.java]

        setContent {
            CS330DZ14AnteaPrimorac5157Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}
package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.HomeScreen
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.theme.CS330DZ14AnteaPrimorac5157Theme
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CS330DZ14AnteaPrimorac5157Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}
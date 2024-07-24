package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.CompanyListPage
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.theme.CS330DZ14AnteaPrimorac5157Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CS330DZ14AnteaPrimorac5157Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CompanyListPage(
                        companies =  listOf(
                            Company("1", "TechCorp", "IT Company", 5000000.0, "logo1.png", CompanyType.IT, true),
                            Company("2", "DataCo", "Data Company", 3000000.0, "logo2.png", CompanyType.IT, false)
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
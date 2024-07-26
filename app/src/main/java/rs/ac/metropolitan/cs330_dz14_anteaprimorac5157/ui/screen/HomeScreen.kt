package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel

@Composable
fun HomeScreen(viewModel: AppViewModel) {
    val internetPermissionGranted by viewModel.internetPermissionGranted.observeAsState(initial = false)
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.setInternetPermissionGranted(isGranted)
    }

    val companies = viewModel.companies.observeAsState(emptyList())
    val isLoading = viewModel.isLoading.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (!internetPermissionGranted) {
            InternetPermission(launcher)
        } else {
            CompanyTabs(viewModel)
            if (isLoading.value) {
                Text("Loading...")
            } else {
                CompanyListPage(companies = companies.value)
            }
        }
    }
}

@Composable
private fun InternetPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Internet permission not granted",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.INTERNET) }) {
                Text("Request permission")
            }
        }
    }
}

@Composable
private fun CompanyTabs(viewModel: AppViewModel) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = CompanyType.values()

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, companyType ->
            Tab(
                text = { Text(companyType.name) },
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    viewModel.setTabCompanyType(companyType)
                }
            )
        }
    }
}
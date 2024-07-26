package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.HomeScreen
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: FakeAppViewModel

    @Before
    fun setup() {
        viewModel = FakeAppViewModel()
    }

    @Test
    fun homeScreenDisplaysInternetPermissionRequest() {
        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Internet permission not granted").assertIsDisplayed()
        composeTestRule.onNodeWithText("Request permission").assertIsDisplayed()
    }

    @Test
    fun homeScreenDisplaysTabsWhenPermissionGranted() {
        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        composeTestRule.runOnUiThread {
            viewModel.setInternetPermissionGranted(true)
        }

        CompanyType.values().forEach { companyType ->
            composeTestRule.onNodeWithText(companyType.name).assertIsDisplayed()
        }
    }

    @Test
    fun clickingTabCallsSetTabCompanyType() {
        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        composeTestRule.runOnUiThread {
            viewModel.setInternetPermissionGranted(true)
        }

        CompanyType.values().forEach { companyType ->
            composeTestRule.onNodeWithText(companyType.name).performClick()
            composeTestRule.runOnUiThread {
                assertEquals(
                    "Tab click failed for ${companyType.name}",
                    companyType,
                    viewModel.currentCompanyType
                )
            }
            composeTestRule.waitForIdle()
        }
    }

    class FakeAppViewModel : AppViewModel {
        // StateFlow je laksi za testirati od LiveData, stoga se koristi u ovom fakeu
        private val _companies = MutableStateFlow<List<Company>>(emptyList())
        override val companies: LiveData<List<Company>> = _companies.asLiveData()

        private val _isLoading = MutableStateFlow(false)
        override val isLoading: LiveData<Boolean> = _isLoading.asLiveData()

        private val _internetPermissionGranted = MutableStateFlow(false)
        override val internetPermissionGranted: LiveData<Boolean> =
            _internetPermissionGranted.asLiveData()

        var currentCompanyType: CompanyType? = null

        override fun loadCompanies() {
            _companies.value = CompanyFake.companies
        }

        override fun setTabCompanyType(companyType: CompanyType) {
            currentCompanyType = companyType
            _companies.value = CompanyFake.companies.filter { it.type == companyType }
            println("Tab set to: $companyType")
        }

        override fun setInternetPermissionGranted(granted: Boolean) {
            _internetPermissionGranted.value = granted
        }
    }
}
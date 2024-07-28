package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.HomeScreen
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Inject
    @Named("fake_appviewmodel")
    lateinit var viewModel: AppViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun homeScreenDisplaysInternetPermissionRequest() {
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }

        composeTestRule.onNodeWithText("Internet permission not granted").assertIsDisplayed()
        composeTestRule.onNodeWithText("Request permission").assertIsDisplayed()
    }

    @Test
    fun homeScreenDisplaysTabsWhenPermissionGranted() {
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }

        composeTestRule.runOnUiThread {
            viewModel.setInternetPermissionGranted(true)
        }

        composeTestRule.waitForIdle()

        CompanyType.values().forEach { companyType ->
            composeTestRule.onNodeWithText(companyType.name).assertIsDisplayed()
        }
    }

    @Test
    fun clickingTabCallsSetTabCompanyType() {
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }

        composeTestRule.runOnUiThread {
            viewModel.setInternetPermissionGranted(true)
        }

        composeTestRule.waitForIdle()

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

    @Test
    fun loadingTextDisplayedInitially() {
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }

        composeTestRule.runOnUiThread {
            viewModel.setInternetPermissionGranted(true)
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
    }
}
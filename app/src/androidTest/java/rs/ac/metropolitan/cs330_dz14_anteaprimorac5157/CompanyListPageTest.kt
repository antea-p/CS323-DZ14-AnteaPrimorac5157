package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.CompanyListPage

@RunWith(AndroidJUnit4::class)
class CompanyListPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun companyListPage_displaysCompaniesCorrectly() {
        val companies = listOf(
            Company("1", "TechCorp", "Leading IT solutions", 5000000.0, "https://example.com/logo1.png", CompanyType.IT, true),
            Company("2", "CodeMasters", "Software development experts", 4000000.0, "https://example.com/logo2.png", CompanyType.IT, false),
            Company("3", "DataDriven", "Big data solutions", 7000000.0, "https://example.com/logo3.png", CompanyType.IT, true),
            Company("4", "CloudNine", "Cloud computing services", 9000000.0, "https://example.com/logo4.png", CompanyType.IT, false),
            Company("5", "CyberShield", "Cybersecurity experts", 3000000.0, "https://example.com/logo5.png", CompanyType.IT, true),
            Company("6", "SmartInnovate", "Home IoT solutions", 6000000.0, "https://example.com/logo6.png", CompanyType.IT, true),
        )

        composeTestRule.setContent {
            CompanyListPage(companies = companies)
        }

        composeTestRule.onNodeWithTag("CompanyList").assertExists()

        companies.forEach { company ->
            composeTestRule.onNodeWithTag("CompanyItem_${company.id}").assertExists()
            composeTestRule.onNodeWithTag("CompanyName_${company.id}").assertTextContains(company.name)
            composeTestRule.onNodeWithTag("CompanyDescription_${company.id}").assertTextContains(company.description)
            composeTestRule.onNodeWithTag("CompanyTurnover_${company.id}")
                .assertTextContains("Turnover: ${company.turnover}")

            if (company.isVatPayer) {
                composeTestRule.onNodeWithTag("CompanyVatStatus_${company.id}").assertExists()
            } else {
                composeTestRule.onNodeWithTag("CompanyVatStatus_${company.id}").assertDoesNotExist()
            }
        }
    }

    @Test
    fun companyListPage_handlesEmptyList() {
        composeTestRule.setContent {
            CompanyListPage(companies = emptyList())
        }

        composeTestRule.onNodeWithTag("CompanyList").assertExists()
        composeTestRule.onNodeWithTag("CompanyList").onChildren().assertCountEquals(0)
    }
}
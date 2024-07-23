package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen.CompanyListPage

class CompanyListPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun companyListPage_displaysCorrectNumberOfTabs() {
        composeTestRule.setContent {
            CompanyListPage(companies = CompanyFake.companies)
        }

        CompanyType.values().forEach { companyType ->
            composeTestRule.onNodeWithTag("Tab_${companyType.name}").assertExists()
        }
    }

    @Test
    fun companyListPage_handlesEmptyCompanyList() {
        composeTestRule.setContent {
            CompanyListPage(companies = emptyList())
        }

        CompanyType.values().forEach { companyType ->
            composeTestRule.onNodeWithTag("Tab_${companyType.name}").performClick()
            composeTestRule.waitForIdle()
            composeTestRule.onNodeWithTag("CompanyList").onChildren().assertCountEquals(0)
        }
    }

    @Test
    fun companyListPage_displaysAndSortsITCompaniesCorrectly() {
        composeTestRule.setContent {
            CompanyListPage(companies = CompanyFake.companies)
        }

        composeTestRule.onNodeWithTag("Tab_IT").performClick()
        composeTestRule.waitForIdle()

        // Kompanije u poreznom sistemu su prve, poredane po prometu.
        composeTestRule.onNodeWithTag("CompanyName_3").assertTextContains("DataDriven")
        composeTestRule.onNodeWithTag("CompanyName_6").assertTextContains("SmartInnovate")
        composeTestRule.onNodeWithTag("CompanyName_1").assertTextContains("TechCorp")
        composeTestRule.onNodeWithTag("CompanyName_5").assertTextContains("CyberShield")

        // Kompanije van poreznog sistema slijede nakon onih u poreznom sistemu, te su takodjer poredane po prometu.
        composeTestRule.onNodeWithTag("CompanyName_4").assertTextContains("CloudNine")
        composeTestRule.onNodeWithTag("CompanyName_2").assertTextContains("CodeMasters")

        // Provjeri porezni status nekih kompanija
        composeTestRule.onNodeWithTag("CompanyVatStatus_3").assertExists()
        composeTestRule.onNodeWithTag("CompanyVatStatus_4").assertDoesNotExist()
    }

    @Test
    fun companyListPage_displaysAndSortsEntertainmentCompaniesCorrectly() {
        composeTestRule.setContent {
            CompanyListPage(companies = CompanyFake.companies)
        }

        composeTestRule.onNodeWithTag("Tab_ENTERTAINMENT").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("CompanyName_9").assertTextContains("CineMagic")
        composeTestRule.onNodeWithTag("CompanyName_11").assertTextContains("VirtualReality")
        composeTestRule.onNodeWithTag("CompanyName_8").assertTextContains("GameWorld")

        composeTestRule.onNodeWithTag("CompanyName_10").assertTextContains("MusicMasters")
        composeTestRule.onNodeWithTag("CompanyName_7").assertTextContains("FunZone")

        composeTestRule.onNodeWithTag("CompanyVatStatus_9").assertExists()
        composeTestRule.onNodeWithTag("CompanyVatStatus_7").assertDoesNotExist()
    }

    @Test
    fun companyListPage_displaysAndSortsTradeCompaniesCorrectly() {
        composeTestRule.setContent {
            CompanyListPage(companies = CompanyFake.companies)
        }

        composeTestRule.onNodeWithTag("Tab_TRADE").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("CompanyName_12").assertTextContains("MegaMart")
        composeTestRule.onNodeWithTag("CompanyName_15").assertTextContains("FashionForward")
        composeTestRule.onNodeWithTag("CompanyName_17").assertTextContains("GreenEnergy")
        composeTestRule.onNodeWithTag("CompanyName_14").assertTextContains("FreshFoods")

        composeTestRule.onNodeWithTag("CompanyName_13").assertTextContains("TradeMax")
        composeTestRule.onNodeWithTag("CompanyName_16").assertTextContains("AutoTraders")

        // Skrolaj toliko da se vidi TechGadgets (inace ce test pasti jer cvor nije prikazan)
        composeTestRule.onNodeWithTag("CompanyList").performScrollToIndex(6)
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("CompanyName_18").assertTextContains("TechGadgets")

        // Poskrolaj natrag gore.
        composeTestRule.onNodeWithTag("CompanyList").performScrollToIndex(0)

        composeTestRule.onNodeWithTag("CompanyVatStatus_12").assertExists()
        composeTestRule.onNodeWithTag("CompanyVatStatus_13").assertDoesNotExist()
    }


}
package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.fakes.Fakes
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModelImpl

@OptIn(ExperimentalCoroutinesApi::class)
class AppViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AppViewModel
    private lateinit var useCasesFake: UseCases

    @Before
    fun setup() {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        useCasesFake = Fakes.UseCasesFake()
        viewModel = AppViewModelImpl(useCasesFake)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialState_companiesEmptyAndNotLoading() {
        assertTrue(viewModel.companies.value?.isEmpty() == true)
        assertFalse(viewModel.isLoading.value == true)
        assertFalse(viewModel.internetPermissionGranted.value == true)
    }

    @Test
    fun setInternetPermissionGranted_loadsCompanies() = runTest {
        // When
        viewModel.setInternetPermissionGranted(true)
        advanceUntilIdle()

        // Then
        assertTrue(viewModel.internetPermissionGranted.value == true)
        assertFalse(viewModel.isLoading.value == true)
        assertNotNull(viewModel.companies.value)
        assertTrue(viewModel.companies.value?.isNotEmpty() == true)
    }

    @Test
    fun loadCompanies_doesNotLoadWithoutPermission() = runTest {
        // When
        viewModel.loadCompanies()
        advanceUntilIdle()

        // Then
        assertFalse(viewModel.isLoading.value == true)
        assertTrue(viewModel.companies.value?.isEmpty() == true)
    }

    @Test
    fun setTabCompanyType_filtersByType() = runTest {
        // Given
        viewModel.setInternetPermissionGranted(true)
        advanceUntilIdle()

        // When
        viewModel.setTabCompanyType(CompanyType.IT)
        advanceUntilIdle()

        // Then
        val companies = viewModel.companies.value
        assertNotNull(companies)
        assertTrue(companies?.isNotEmpty() == true)
        assertTrue(companies?.all { it.type == CompanyType.IT } == true)
    }

    @Test
    fun loadCompanies_successPath_companiesLoadedAndSorted() = runTest {
        // Given
        viewModel.setInternetPermissionGranted(true)
        advanceUntilIdle()

        // When
        viewModel.loadCompanies()
        advanceUntilIdle()

        // Then
        val companies = viewModel.companies.value
        assertNotNull(companies)
        assertTrue(companies?.isNotEmpty() == true)
        assertTrue(companies?.first()?.isVatPayer == true)
        assertTrue((companies?.first()?.turnover ?: 0.0) > 10000.0)
    }
}
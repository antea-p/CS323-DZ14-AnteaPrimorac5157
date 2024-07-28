package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.RepositoryImpl
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RepositoryImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("fake_apiservice")
    lateinit var apiService: ApiService

    @Inject
    lateinit var repository: Repository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `getCompanies returns all companies`() = runTest {
        val companies = repository.loadCompanies().first()
        assertEquals(CompanyFake.companies, companies)
    }
    @Test
    fun loadCompanies_returnsFlowOfCompanies() = runBlocking {
        val companies = repository.loadCompanies().first()
        assertNotNull(companies)
        assertTrue(companies.isNotEmpty())
    }

    @Test
    fun addCompany_addsCompanySuccessfully() = runBlocking {
        val newCompany = Company("100", "NewCorp", "New Company", 1000000.0, "newlogo.png", CompanyType.TRADE, false)
        repository.add(newCompany)

        val companies = repository.loadCompanies().first()
        assertTrue(companies.contains(newCompany))
    }

    @Test
    fun deleteCompany_removesCompanySuccessfully() = runBlocking {
        val companyToDelete = repository.loadCompanies().first().first()
        repository.delete(companyToDelete.id)

        val companies = repository.loadCompanies().first()
        assertFalse(companies.contains(companyToDelete))
    }
}
package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.di.Fakes
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCasesImpl
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class UseCasesImplTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var useCases: UseCases

    @Inject
    @Named("fake_repository")
    lateinit var repository: Repository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `getCompanies returns all companies`() = runTest {
        val companies = useCases.getCompanies().first()
        assertEquals(CompanyFake.companies.size, companies.size)
    }

    @Test
    fun `getCompaniesByType returns only companies of specified type`() = runTest {
        val itCompanies = useCases.getCompaniesByType(CompanyType.IT).first()
        assertTrue(itCompanies.all { it.type == CompanyType.IT })
    }

    @Test
    fun `companies are sorted by VAT status and turnover`() = runTest {
        val companies = useCases.getCompanies().first()
        val sortedCompanies = companies.sortedWith(
            compareByDescending<Company> { it.isVatPayer }
                .thenByDescending { it.turnover }
        )
        assertEquals(sortedCompanies, companies)
    }
}
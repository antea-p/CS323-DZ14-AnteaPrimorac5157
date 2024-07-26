package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCasesImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.fakes.Fakes

class UseCasesImplTest {
    private lateinit var useCases: UseCases
    private lateinit var repository: Repository

    @Before
    fun setup() {
        repository = Fakes.FakeRepository()
        useCases = UseCasesImpl(repository)
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
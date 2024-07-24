package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake

class BusinessRulesTest {

    @Test
    fun `filterCompaniesByType returns only companies of specified type`() = runTest {
        val companies = flowOf(CompanyFake.companies)
        val filteredCompanies = BusinessRules.filterCompaniesByType(companies, CompanyType.IT).first()

        assertEquals(6, filteredCompanies.size)
        assertTrue(filteredCompanies.all { it.type == CompanyType.IT })
    }

    @Test
    fun `sortCompaniesByVatStatusAndTurnover sorts correctly`() = runTest {
        val companies = flowOf(CompanyFake.companies.filter { it.type == CompanyType.IT })
        val sortedCompanies = BusinessRules.sortCompaniesByVatStatusAndTurnover(companies).first()

        // Check if VAT-paying companies come first
        assertTrue(sortedCompanies.takeWhile { it.isVatPayer }.size >= sortedCompanies.dropWhile { it.isVatPayer }.size)

        // Check if companies are sorted by turnover within each VAT group
        fun isSortedByTurnover(list: List<Company>): Boolean {
            return list.zipWithNext { a, b -> a.turnover >= b.turnover }.all { it }
        }

        val vatPayers = sortedCompanies.filter { it.isVatPayer }
        val nonVatPayers = sortedCompanies.filter { !it.isVatPayer }

        assertTrue(isSortedByTurnover(vatPayers))
        assertTrue(isSortedByTurnover(nonVatPayers))
    }

    @Test
    fun `applyBusinessRules returns correctly filtered and sorted companies`() = runTest {
        val companies = flowOf(CompanyFake.companies)
        val result = BusinessRules.applyBusinessRules(companies, CompanyType.IT).first()

        // Check if all companies are of type IT
        assertTrue(result.all { it.type == CompanyType.IT })

        // Check if VAT-paying companies come first
        val vatPayerIndex = result.indexOfLast { it.isVatPayer }
        val nonVatPayerIndex = result.indexOfFirst { !it.isVatPayer }
        assertTrue(vatPayerIndex < nonVatPayerIndex || nonVatPayerIndex == -1)

        // Check if companies are sorted by turnover within each VAT group
        fun isSortedByTurnover(list: List<Company>): Boolean {
            return list.zipWithNext { a, b -> a.turnover >= b.turnover }.all { it }
        }

        val vatPayers = result.filter { it.isVatPayer }
        val nonVatPayers = result.filter { !it.isVatPayer }

        assertTrue(isSortedByTurnover(vatPayers))
        assertTrue(isSortedByTurnover(nonVatPayers))

        // Check specific order based on the example
        assertEquals("DataDriven", result[0].name)
        assertEquals("SmartInnovate", result[1].name)
        assertEquals("TechCorp", result[2].name)
        assertEquals("CyberShield", result[3].name)
        assertEquals("CloudNine", result[4].name)
        assertEquals("CodeMasters", result[5].name)
    }
}
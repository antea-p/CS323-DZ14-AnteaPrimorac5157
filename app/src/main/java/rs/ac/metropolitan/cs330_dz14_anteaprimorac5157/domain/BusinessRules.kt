package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType

object BusinessRules {

    fun filterCompaniesByType(companies: Flow<List<Company>>, type: CompanyType): Flow<List<Company>> {
        return companies.map { list ->
            list.filter { it.type == type }
        }
    }

    fun sortCompaniesByVatStatusAndTurnover(companies: Flow<List<Company>>): Flow<List<Company>> {
        return companies.map { list ->
            list.sortedWith(
                compareByDescending<Company> { it.isVatPayer }
                    .thenByDescending { it.turnover }
            )
        }
    }

    fun applyBusinessRules(companies: Flow<List<Company>>, type: CompanyType): Flow<List<Company>> {
        return companies
            .let { filterCompaniesByType(it, type) }
            .let { sortCompaniesByVatStatusAndTurnover(it) }
    }
}
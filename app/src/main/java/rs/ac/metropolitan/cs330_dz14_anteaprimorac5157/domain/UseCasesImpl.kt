package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import javax.inject.Inject

class UseCasesImpl @Inject constructor(
    private val repository: Repository
) : UseCases {
    override suspend fun getCompanies(): Flow<List<Company>> {
        return repository.loadCompanies().map { companies ->
            companies.sortedWith(
                compareByDescending<Company> { it.isVatPayer }
                    .thenByDescending { it.turnover }
            )
        }
    }

    override suspend fun getCompaniesByType(type: CompanyType): Flow<List<Company>> {
        return repository.loadCompanies().map { companies ->
            companies.filter { it.type == type }
                .sortedWith(
                    compareByDescending<Company> { it.isVatPayer }
                        .thenByDescending { it.turnover }
                )
        }
    }
}
package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    private var companiesFlow: Flow<List<Company>> = flowOf(listOf<Company>())

    override suspend fun loadCompanies(): Flow<List<Company>> {
        if (companiesFlow.first().isEmpty()) {
            companiesFlow = flowOf(apiService.getAll())
        }
        return companiesFlow
    }

    override suspend fun add(company: Company) {
        apiService.add(company)
        companiesFlow = flowOf(apiService.getAll())
    }

    override suspend fun delete(id: String) {
        apiService.delete(id)
        companiesFlow = flowOf(apiService.getAll())
    }
}
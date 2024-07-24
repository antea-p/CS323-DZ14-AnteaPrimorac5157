package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType

interface UseCases {
    suspend fun getCompanies(): Flow<List<Company>>
    suspend fun getCompaniesByType(type: CompanyType): Flow<List<Company>>
}
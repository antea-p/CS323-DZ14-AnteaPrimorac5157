package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company

interface Repository {
    suspend fun loadCompanies(): Flow<List<Company>>
    suspend fun add(company: Company)
    suspend fun delete(id: String)
}
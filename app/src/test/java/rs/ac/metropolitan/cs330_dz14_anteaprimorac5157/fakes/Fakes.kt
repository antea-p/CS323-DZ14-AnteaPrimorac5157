package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.fakes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases

object Fakes {
    class UseCasesFake : UseCases {
        private val fakeCompanies = CompanyFake.companies;

        override suspend fun getCompanies(): Flow<List<Company>> {
            return flowOf(fakeCompanies)
        }

        override suspend fun getCompaniesByType(type: CompanyType): Flow<List<Company>> {
            return flowOf(fakeCompanies.filter { it.type == type })
        }
    }
}
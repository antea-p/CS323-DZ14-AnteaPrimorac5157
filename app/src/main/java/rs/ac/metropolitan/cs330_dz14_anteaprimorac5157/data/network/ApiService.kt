package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company

interface ApiService {

    @GET(Constants.COMPANIES_URL)
    suspend fun getAll(): List<Company>

    @POST(Constants.COMPANIES_URL)
    suspend fun add(@Body company: Company)

    @DELETE("${Constants.COMPANIES_URL}/{id}")
    suspend fun delete(@Path("id") id: String)
}
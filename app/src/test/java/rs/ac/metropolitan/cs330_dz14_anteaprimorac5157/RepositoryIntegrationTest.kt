package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.RepositoryImpl

class RepositoryApiIntegrationTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var repository: Repository
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        repository = RepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `repository correctly fetches companies from API`() = runBlocking {
        val mockResponse = """
            [
                {
                    "id": "1",
                    "name": "TechCorp",
                    "description": "IT Company",
                    "turnover": 5000000.0,
                    "logoUrl": "logo1.png",
                    "type": "IT",
                    "isVatPayer": true
                },
                {
                    "id": "2",
                    "name": "EntertainCo",
                    "description": "Entertainment Company",
                    "turnover": 3000000.0,
                    "logoUrl": "logo2.png",
                    "type": "ENTERTAINMENT",
                    "isVatPayer": false
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val companies = repository.loadCompanies().first()

        assertEquals(2, companies.size)
        assertTrue(companies.any { it.name == "TechCorp" && it.type == CompanyType.IT })
        assertTrue(companies.any { it.name == "EntertainCo" && it.type == CompanyType.ENTERTAINMENT })

        val request = mockWebServer.takeRequest()
        assertEquals("/companies", request.path)
    }
}
package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.Common

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getAll returns list of companies`() = runBlocking {
        val mockResponse = """
            [
                {
                    "id": "1",
                    "name": "TechCorp",
                    "description": "IT Solutions",
                    "turnover": 1000000.0,
                    "logoUrl": "https://example.com/logo1.png",
                    "type": "IT",
                    "isVatPayer": true
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val companies = apiService.getAll()

        assertEquals(1, companies.size)
        assertEquals("TechCorp", companies[0].name)
    }

    @Test
    fun `add successfully adds a company`() = runBlocking {
        val company = Company("2", "NewCorp", "New Business", 500000.0, Common.generateAvatarImage("NewCorp").toString(), CompanyType.TRADE, false)

        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        val response = apiService.add(company)

        val request = mockWebServer.takeRequest()
        assertEquals("POST", request.method)
        assertEquals("/companies", request.path)
    }

    @Test
    fun `delete successfully removes a company`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        apiService.delete("1")

        val request = mockWebServer.takeRequest()
        assertEquals("DELETE", request.method)
        assertEquals("/companies/1", request.path)
    }
}
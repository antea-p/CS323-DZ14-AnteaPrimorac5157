package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class RetrofitHelperImpl : RetrofitHelper {
    override fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }

    private val gson = GsonBuilder().create()
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor.Level.BODY
                        )
                    )
                    .build()
            )
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}


interface RetrofitHelper {
    fun getApiService(): ApiService
}
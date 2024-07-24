package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.di

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.RetrofitHelper
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.RetrofitHelperImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.RepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppHiltModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext

    }

    @Provides
    @Singleton
    fun provideRetrofitHelper(): RetrofitHelper {
        Log.d("AppHiltModule", "Providing RetrofitHelper")
        return RetrofitHelperImpl()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofitHelper: RetrofitHelper): ApiService {
        Log.d("AppHiltModule", "Providing ApiService")
        return retrofitHelper.getApiService()
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        Log.d("AppHiltModule", "Providing Repository")
        return RepositoryImpl(apiService)
    }

}
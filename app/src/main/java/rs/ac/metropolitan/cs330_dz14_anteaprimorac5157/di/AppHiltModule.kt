package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppHiltModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext

    }

}
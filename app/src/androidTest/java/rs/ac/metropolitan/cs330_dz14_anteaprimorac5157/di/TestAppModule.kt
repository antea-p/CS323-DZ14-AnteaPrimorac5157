package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.network.ApiService
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.Repository
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.repository.RepositoryImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCasesImpl
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModelImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppHiltModule::class]
)
object TestAppModule {

    private const val TAG = "TestAppModule"

    @Provides
    @Singleton
    @Named("fake_appviewmodel")
    fun provideFakeAppViewModel(): AppViewModel {
        Log.d(TAG, "Providing FAKE AppViewModel")
        return Fakes.AppViewModelFake()
    }

    @Provides
    @Singleton
    fun provideAppViewModel(useCases: UseCases): AppViewModel {
        Log.d(TAG, "Providing Real AppViewModel")
        return AppViewModelImpl(useCases)
    }

    @Provides
    @Singleton
    @Named("fake_apiservice")
    fun provideFakeApiService(): ApiService {
        Log.d(TAG, "Providing FAKE ApiService")
        return Fakes.ApiServiceFake()
    }

    @Provides
    @Singleton
    @Named("fake_repository")
    fun provideFakeRepository(): Repository {
        Log.d(TAG, "Providing FAKE Repository")
        return Fakes.RepositoryFake()
    }

    @Provides
    @Singleton
    fun provideRepository(@Named("fake_apiservice") apiService: ApiService): Repository {
        Log.d(TAG, "Providing Real Repository")
        return RepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    @Named("fake_usecases")
    fun provideFakeUseCases(): UseCases {
        Log.d(TAG, "Providing FAKE UseCases")
        return Fakes.UseCasesFake()
    }

    @Provides
    @Singleton
    fun provideUseCases(@Named("fake_repository") repository: Repository): UseCases {
        Log.d(TAG, "Providing Real UseCases")
        return UseCasesImpl(repository)
    }
}
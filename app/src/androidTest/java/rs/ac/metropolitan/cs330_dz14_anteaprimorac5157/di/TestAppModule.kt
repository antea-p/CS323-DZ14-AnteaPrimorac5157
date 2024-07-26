package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppHiltModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    @Named("fake_appviewmodel")
    fun provideAppViewModel(): AppViewModel = Fakes.AppViewModelFake()

    @Provides
    @Singleton
    fun provideUseCases(): UseCases = Fakes.UseCasesFake()

}
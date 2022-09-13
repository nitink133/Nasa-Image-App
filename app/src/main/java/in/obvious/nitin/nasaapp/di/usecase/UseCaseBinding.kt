package `in`.obvious.nitin.nasaapp.di.usecase

import `in`.obvious.nitin.nasaapp.usecase.GetImagesUseCase
import `in`.obvious.nitin.nasaapp.usecase.GetImagesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface UseCaseBinding {

    @Binds
    fun getImagesListUseCase(useCase: GetImagesUseCaseImpl): GetImagesUseCase
}
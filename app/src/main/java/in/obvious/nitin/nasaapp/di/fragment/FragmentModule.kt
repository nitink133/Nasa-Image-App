package `in`.obvious.nitin.nasaapp.di.fragment

import `in`.obvious.nitin.nasaapp.di.quantifier.FragmentViewModelFactory
import `in`.obvious.nitin.nasaapp.utils.functional.MyViewModelFactory
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    @FragmentScoped
    @FragmentViewModelFactory
    fun viewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory = factory
}

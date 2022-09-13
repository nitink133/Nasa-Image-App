package `in`.obvious.nitin.nasaapp.di.fragment

import `in`.obvious.nitin.nasaapp.di.quantifier.ViewModelKey
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageDetailsViewModel
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageDetailsViewModelImpl
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageListViewModel
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageListViewModelImpl
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    fun bindImageListViewModel(viewModel: ImageListViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailsViewModel::class)
    fun bindImageDetailsViewModel(viewModel: ImageDetailsViewModelImpl): ViewModel
}
package `in`.obvious.nitin.nasaapp.ui.images.viewmodel

import `in`.obvious.nitin.nasaapp.base.viewmodel.BaseViewModel
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.usecase.GetImagesUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
abstract class ImageListViewModel : BaseViewModel() {
    abstract val imagesList: LiveData<List<NasaImage>>
}

class ImageListViewModelImpl @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ImageListViewModel() {
    override val imagesList: MutableLiveData<List<NasaImage>> = MutableLiveData(emptyList())

    init {
        launch {
            imagesList.value = getImagesUseCase.getImages()
        }
    }

}
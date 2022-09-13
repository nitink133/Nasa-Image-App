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
abstract class ImageDetailsViewModel : BaseViewModel() {
    abstract val imagesList: LiveData<List<NasaImage>>
}

class ImageDetailsViewModelImpl @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ImageDetailsViewModel() {
    override val imagesList: MutableLiveData<List<NasaImage>> = MutableLiveData()

    init {
        launch {
            imagesList.value = getImagesUseCase.getImages()
        }
    }
}
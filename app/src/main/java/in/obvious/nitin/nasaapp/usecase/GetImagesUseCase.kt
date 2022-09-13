package `in`.obvious.nitin.nasaapp.usecase

import `in`.obvious.nitin.nasaapp.constants.ResourceFile
import `in`.obvious.nitin.nasaapp.model.domain.NasaImageDomainModel
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.utils.functional.ResourceUtils
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface GetImagesUseCase {
    suspend fun getImages(): List<NasaImage>
}

class GetImagesUseCaseImpl @Inject constructor(@ApplicationContext val context: Context) :
    GetImagesUseCase {
    override suspend fun getImages(): List<NasaImage> {
        return (ResourceUtils.readResourceFile<NasaImageDomainModel>(
            context = context,
            resourceFile = ResourceFile.IMAGE_DATA
        ) as? List<NasaImageDomainModel>)?.map {
            with(it) {
                NasaImage(
                    copyright,
                    date,
                    explanation,
                    hdurl,
                    media_type,
                    service_version,
                    title,
                    url
                )
            }
        } ?: emptyList()
    }
}
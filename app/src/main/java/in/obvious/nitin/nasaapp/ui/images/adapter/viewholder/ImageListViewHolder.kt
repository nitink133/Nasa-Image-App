package `in`.obvious.nitin.nasaapp.ui.images.adapter.viewholder

import `in`.obvious.nitin.nasaapp.databinding.ItemImagesBinding
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.utils.functional.AnimationUtils
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
class ImageListViewHolder(
    private val binding: ItemImagesBinding,
    private val onClick: ((imageView: View, position: Int, transitionId: String) -> Unit),
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaImage) = with(binding) {
        var isImageLoaded = false
        tvTitle.text = item.title
        ivThumbnail.load(item.url) {
            listener(
                onError = { _, _ ->
                    AnimationUtils.hideView(progressBar)
                },
                onSuccess = { _, _ ->
                    isImageLoaded = true
                    ViewCompat.setTransitionName(ivThumbnail, "ivLogoTransition_${position}_${item.title}")
                    AnimationUtils.hideView(progressBar)
                }
            )
        }
        root.setOnClickListener {
            if(!isImageLoaded)return@setOnClickListener
            onClick.invoke(ivThumbnail, adapterPosition, "ivLogoTransition_${position}_${item.title}")
        }
    }
}
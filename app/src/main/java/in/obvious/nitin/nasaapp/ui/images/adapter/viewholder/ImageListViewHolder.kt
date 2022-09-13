package `in`.obvious.nitin.nasaapp.ui.images.adapter.viewholder

import `in`.obvious.nitin.nasaapp.databinding.ItemImagesBinding
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.utils.functional.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
class ImageListViewHolder(
    private val binding: ItemImagesBinding,
    private val onClick: ((position: Int) -> Unit),
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaImage) = with(binding) {
        tvTitle.text = item.title
        AnimationUtils.showView(progressBar)
        ivThumbnail.load(item.url) {
            listener(
                onError = { _, _ ->
                    AnimationUtils.hideView(progressBar)
                },
                onSuccess = { _, _ ->
                    AnimationUtils.hideView(progressBar)
                }
            )
        }
        root.setOnClickListener {
            onClick.invoke(adapterPosition)
        }
    }
}
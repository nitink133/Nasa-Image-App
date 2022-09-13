package `in`.obvious.nitin.nasaapp.ui.images.adapter.viewholder

import `in`.obvious.nitin.nasaapp.databinding.ItemImageDetailsBinding
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.utils.functional.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
class ImageDetailPagerViewHolder(
    private val binding: ItemImageDetailsBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaImage) = with(binding) {
        tvTitle.text = item.title
        tvDate.text = item.date
        tvExplanation.text = item.explanation
        AnimationUtils.showView(progressBar)
        ivLogo.load(item.url) {
            listener(
                onError = { _, _ ->
                    AnimationUtils.hideView(progressBar)
                },
                onSuccess = { _, _ ->
                    AnimationUtils.hideView(progressBar)
                }
            )
        }
        tvCopyright.text = item.copyright
        lblCopyright.isVisible = item.copyright != null
    }
}
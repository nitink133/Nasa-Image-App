package `in`.obvious.nitin.nasaapp.ui.images.adapter

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.databinding.ItemImagesBinding
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.ui.images.adapter.viewholder.ImageListViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
class ImageListAdapter(
    private val onClick: ((position: Int) -> Unit),
) : ListAdapter<NasaImage, ImageListViewHolder>(object : DiffUtil.ItemCallback<NasaImage>() {

    override fun areItemsTheSame(oldItem: NasaImage, newItem: NasaImage): Boolean {
        return oldItem.explanation == newItem.explanation
    }


    override fun areContentsTheSame(oldItem: NasaImage, newItem: NasaImage): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)
        return ImageListViewHolder(
            ItemImagesBinding.bind(view),
            onClick,
        )
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

package `in`.obvious.nitin.nasaapp.ui.images.adapter

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.databinding.ItemImageDetailsBinding
import `in`.obvious.nitin.nasaapp.model.ui.NasaImage
import `in`.obvious.nitin.nasaapp.ui.images.adapter.viewholder.ImageDetailPagerViewHolder
import `in`.obvious.nitin.nasaapp.utils.functional.AnimationUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
class ImageDetailsPagerAdapter() :
    ListAdapter<NasaImage, ImageDetailPagerViewHolder>(object :
        DiffUtil.ItemCallback<NasaImage>() {

        override fun areItemsTheSame(oldItem: NasaImage, newItem: NasaImage): Boolean {
            return oldItem.explanation == newItem.explanation
        }


        override fun areContentsTheSame(oldItem: NasaImage, newItem: NasaImage): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_details, parent, false)
        return ImageDetailPagerViewHolder(ItemImageDetailsBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ImageDetailPagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

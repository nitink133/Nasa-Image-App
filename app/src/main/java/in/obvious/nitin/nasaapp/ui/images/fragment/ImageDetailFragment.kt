package `in`.obvious.nitin.nasaapp.ui.images.fragment

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.base.fragment.BaseFragment
import `in`.obvious.nitin.nasaapp.databinding.FragmentImageDetailBinding
import `in`.obvious.nitin.nasaapp.databinding.FragmentImagesListBinding
import `in`.obvious.nitin.nasaapp.ui.images.adapter.ImageDetailsPagerAdapter
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageDetailsViewModel
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageListViewModel
import `in`.obvious.nitin.nasaapp.utils.extentions.subscribe
import `in`.obvious.nitin.nasaapp.utils.functional.ZoomOutPageTransformer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2

import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
@AndroidEntryPoint
class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding, ImageDetailsViewModel>() {
    private val mAdapter: ImageDetailsPagerAdapter by lazy { ImageDetailsPagerAdapter() }
    private var currentPage: Int = 0
    private var size: Int = 0

    @Inject
    lateinit var args: ImageDetailFragmentArgs

    override val bindingInflater =
        { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean ->
            FragmentImageDetailBinding.inflate(layoutInflater, viewGroup, b)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSubscriptions()
        initListeners()
    }

    private fun initView() {
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun initSubscriptions() {
        subscribe(viewModel.imagesList) {
            mAdapter.submitList(it)
            size += it.size - 1
            binding.viewPager.apply {
                adapter = mAdapter
                setCurrentItem(args.position, false)
                setPageTransformer(ZoomOutPageTransformer())
            }
        }
    }

    private fun initListeners() = with(binding) {
        ivBack.setOnClickListener {
            viewPager.setCurrentItem(currentPage - 1, true)
        }
        ivNext.setOnClickListener {
            viewPager.setCurrentItem(currentPage + 1, true)
        }
    }

    private val pageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentPage = position
                when (position) {
                    0 -> { // first item
                        binding.ivBack.isVisible = false
                    }
                    size -> { // last item
                        binding.ivNext.isVisible = false
                    }
                    else -> {
                        binding.ivBack.isVisible = true
                        binding.ivNext.isVisible = true
                    }
                }
                binding.tvToolbarTitle.text = mAdapter.currentList[position].title
            }
        }

}
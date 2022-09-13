package `in`.obvious.nitin.nasaapp.ui.images.fragment

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.base.fragment.BaseFragment
import `in`.obvious.nitin.nasaapp.databinding.FragmentImagesListBinding
import `in`.obvious.nitin.nasaapp.ui.images.adapter.ImageListAdapter
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageListViewModel
import `in`.obvious.nitin.nasaapp.utils.extentions.subscribe
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
@AndroidEntryPoint
class ImagesListFragment : BaseFragment<FragmentImagesListBinding, ImageListViewModel>() {
    override val bindingInflater =
        { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean ->
            FragmentImagesListBinding.inflate(layoutInflater, viewGroup, b)
        }

    private val mAdapter: ImageListAdapter by lazy {
        ImageListAdapter(
            mAdapterSelectionCallback
        )
    }

    private val mAdapterSelectionCallback: ((imageView: View, position: Int, transitionId: String) -> Unit) =
        let@{ imageView, position, transitionId ->
            val direction = ImagesListFragmentDirections.moveToImageDetail(transitionId, position)
            val extras = FragmentNavigatorExtras(
                imageView to transitionId
            )
            findNavController().navigate(direction, extras)
        }

    override fun showProgress(msg: String?) {
        //ignore
    }

    override fun hideProgress() {
        //ignore
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initSubscriptions()
    }

    private fun initView() {
        binding.rvImages.apply {
            adapter = mAdapter
            layoutManager =
                GridLayoutManager(requireContext(), resources.getInteger(R.integer.span_count))
        }
    }

    private fun initSubscriptions() {
        subscribe(viewModel.imagesList) {
            mAdapter.submitList(it)
        }
    }


}
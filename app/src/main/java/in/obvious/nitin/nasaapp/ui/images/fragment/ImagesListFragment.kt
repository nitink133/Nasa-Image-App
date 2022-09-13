package `in`.obvious.nitin.nasaapp.ui.images.fragment

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.base.fragment.BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ImagesListFragment : BaseFragment() {
    override fun showProgress(msg: String?) {
        //ignore
    }

    override fun hideProgress() {
        //ignore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images_list, container, false)
    }
}
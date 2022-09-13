package `in`.obvious.nitin.nasaapp.ui

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.base.activity.BaseActivity
import `in`.obvious.nitin.nasaapp.base.fragment.BaseFragment
import `in`.obvious.nitin.nasaapp.databinding.ActivityMainBinding
import `in`.obvious.nitin.nasaapp.ui.images.fragment.ImagesListFragment
import `in`.obvious.nitin.nasaapp.utils.functional.ToastUtils
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragment = getVisibleFragment()
        if (supportFragmentManager.backStackEntryCount > 0 || fragment !is ImagesListFragment) {
            super.onBackPressed()
        } else if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true
            ToastUtils.showMessage(this, getString(R.string.msg_please_press_again_to_exit))
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
            return
        }
    }


    private fun getVisibleFragment(): Fragment? {
        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if (navHostFragment?.childFragmentManager?.fragments.isNullOrEmpty()) return null
        return navHostFragment?.childFragmentManager?.fragments!![0]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
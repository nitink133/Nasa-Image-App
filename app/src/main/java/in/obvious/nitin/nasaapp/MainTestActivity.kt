package `in`.obvious.nitin.nasaapp

import `in`.obvious.nitin.nasaapp.base.activity.BaseActivity
import `in`.obvious.nitin.nasaapp.databinding.ActivityMainTestBinding
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTestActivity : BaseActivity() {
    private lateinit var binding: ActivityMainTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main_test
        )
    }

    fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }
}

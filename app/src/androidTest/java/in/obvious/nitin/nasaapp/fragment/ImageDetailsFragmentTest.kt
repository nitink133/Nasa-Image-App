package `in`.obvious.nitin.nasaapp.fragment

import `in`.obvious.nitin.nasaapp.MainTestActivity
import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.ui.images.fragment.ImageDetailFragment
import `in`.obvious.nitin.nasaapp.ui.images.fragment.ImageDetailFragmentArgs
import `in`.obvious.nitin.nasaapp.utils.runBlockingTest
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ImageDetailsFragmentTest {

    @Before
    fun init() {
        //ignore
    }

    private fun startFragment(position: Int) {
        ActivityScenario.launch(MainTestActivity::class.java).onActivity {
            val loginFragment = ImageDetailFragment().apply {
                arguments = ImageDetailFragmentArgs(position).toBundle()
            }
            it.openFragment(loginFragment)
        }
    }

    @Test
    fun test_if_view_pager_visible() = runBlockingTest {
        startFragment(0)
        onView(withId(R.id.view_pager))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_back_button_should_not_be_visible_for_first_item() = runBlockingTest {
        startFragment(0)
        onView(withId(R.id.iv_back))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun test_next_button_should_not_be_visible_for_last_item() = runBlockingTest {
        startFragment(25)
        onView(withId(R.id.iv_next))
            .check(matches(not(isDisplayed())))
    }

}
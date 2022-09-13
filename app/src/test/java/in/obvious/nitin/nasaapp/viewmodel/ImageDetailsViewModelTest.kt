package `in`.obvious.nitin.nasaapp.viewmodel

import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageDetailsViewModel
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageDetailsViewModelImpl
import `in`.obvious.nitin.nasaapp.usecase.GetImagesUseCase
import `in`.obvious.nitin.nasaapp.utils.getOrAwaitValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class ImageDetailsViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getImagesUseCase: GetImagesUseCase

    lateinit var viewModel: ImageDetailsViewModel

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = ImageDetailsViewModelImpl(getImagesUseCase)
    }

    @Test
    fun test_get_images_list_notNull() {
        val value = viewModel.imagesList.getOrAwaitValue()
        Assert.assertNotNull(value)
    }

    @Test
    fun test_get_images_list_isNotEmpty() {
        val value = viewModel.imagesList.getOrAwaitValue()
        assert(value?.isNotEmpty() == true)
    }
}
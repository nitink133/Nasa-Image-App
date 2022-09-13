package `in`.obvious.nitin.nasaapp.viewmodel

import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageListViewModel
import `in`.obvious.nitin.nasaapp.ui.images.viewmodel.ImageListViewModelImpl
import `in`.obvious.nitin.nasaapp.usecase.GetImagesUseCase
import `in`.obvious.nitin.nasaapp.usecase.GetImagesUseCaseImpl
import `in`.obvious.nitin.nasaapp.utils.getOrAwaitValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.robolectric.annotation.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.io.InputStream
import javax.inject.Inject


@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class ImagesListViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getImagesUseCase: GetImagesUseCase

    lateinit var viewModel: ImageListViewModel

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = ImageListViewModelImpl(getImagesUseCase)
    }

    @Test
    fun test_get_images_list_notNull() {
        val value = viewModel.imagesList.getOrAwaitValue()
        assertNotNull(value)
    }

    @Test
    fun test_get_images_list_isNotEmpty() {
        val value = viewModel.imagesList.getOrAwaitValue()
        assert(value?.isNotEmpty() == true)
    }
}
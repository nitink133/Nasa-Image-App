package `in`.obvious.nitin.nasaapp.base.fragment

import `in`.obvious.nitin.nasaapp.R
import `in`.obvious.nitin.nasaapp.base.viewmodel.BaseViewModel
import `in`.obvious.nitin.nasaapp.databinding.FragmentBaseBinding
import `in`.obvious.nitin.nasaapp.di.quantifier.FragmentViewModelFactory
import `in`.obvious.nitin.nasaapp.utils.extentions.subscribe
import `in`.obvious.nitin.nasaapp.utils.functional.ToastUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private val defaultErrorHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    private var _binding: VB? = null

    protected val binding get() = requireNotNull(_binding)

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Inject
    @FragmentViewModelFactory
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    abstract fun showProgress(msg: String?)
    abstract fun hideProgress()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = FragmentBaseBinding.inflate(layoutInflater)
        _binding = bindingInflater.invoke(inflater, container, false)
        root.root.addView(
            binding.root, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        viewModel = ViewModelProvider(this, factory).get(getViewModelKClass().java)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        subscribe(viewModel.defaultErrorLiveData) {
            onError(it)
        }

        return root.root
    }

    @Suppress("UNCHECKED_CAST")
    protected fun getViewModelKClass(): KClass<VM> {
        val actualClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        return actualClass.kotlin
    }

    protected open fun onError(throwable: Throwable) {
        try {
            ToastUtils.showMessage(context = context, getString(R.string.err_something_went_wrong))
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return viewLifecycleOwner.lifecycleScope.launch(defaultErrorHandler) {
            this.block()
        }
    }

    protected fun launchSilent(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch(defaultErrorHandler) {
            this.block()
        }
    }

    protected fun launchForFlow(block: suspend CoroutineScope.() -> Unit): Job = launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this.block()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
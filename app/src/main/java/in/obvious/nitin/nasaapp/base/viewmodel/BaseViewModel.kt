package `in`.obvious.nitin.nasaapp.base.viewmodel

import `in`.obvious.nitin.nasaapp.utils.functional.SingleLiveEvent
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val _defaultErrorLiveData = SingleLiveEvent<Throwable>()

    val defaultErrorLiveData: LiveData<Throwable> = _defaultErrorLiveData

    private val defaultErrorHandler = CoroutineExceptionHandler { _, throwable ->
        _defaultErrorLiveData.postValue(throwable)
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(defaultErrorHandler) {
            this.block()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreateView() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @CallSuper
    open fun onDestroyView() {
    }
}

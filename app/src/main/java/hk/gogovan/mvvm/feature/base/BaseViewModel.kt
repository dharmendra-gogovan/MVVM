package hk.gogovan.mvvm.feature.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base ViewModel for initialization and base configurations
 * ViewModel has it's own life-cycle apart from activity
 * @see <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a>
 */
abstract class BaseViewModel<V : BaseView> : ViewModel() {

  // Interface for communicating with model
  protected var view: V? = null

  // Loading Dialog
  private val isLoading: MutableLiveData<Boolean> = MutableLiveData()

  // Composite Disposable
  // It is used for adding disposables which can be removed/cleared
  // when viewModel get destroyed
  protected var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private set

  /**
   * On model attached to ViewModel
   * Attach view context
   * @param v View context
   */
  fun onAttach(v: V?) {
    this.view = v
  }

  /**
   * On model de-attached from ViewModel
   * Detach view context
   */
  fun onDetach() {
    // Clear model interface
    this.view = null
  }

  /**
   * On ViewModel Cleared
   * Clear compositeDisposables
   */
  override fun onCleared() {

    this.onDetach()
    // Clear All Disposables
    this.compositeDisposable?.clear()
    this.compositeDisposable = null

    // Run Super OnCleared()
    super.onCleared()
  }

  /**
   * Get IsView Loading
   */
  protected fun getIsLoading(): MutableLiveData<Boolean> {
    return this.isLoading
  }

  /**
   * Set View Loading True
   * @param value true/false for show/hide
   */
  protected fun setIsLoading(value: Boolean) {
    this.isLoading.postValue(value)
  }

}

package hk.gogovan.mvvm.feature.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import hk.gogovan.mvvm.BR
import hk.gogovan.mvvm.ui.LoadingDialog
import hk.gogovan.mvvm.utils.AppPrefs
import javax.inject.Inject

/**
 * Base Activity for initialization and base configurations
 */
abstract class BaseActivity<V : BaseView, VM : BaseViewModel<V>, B : ViewDataBinding> :
  DaggerAppCompatActivity(),
  BaseView {

  /**
   * Shared Preferences property
   * To get app-level user values
   */
  @Inject
  lateinit var appPrefs: AppPrefs

  /**
   * Binding member property
   * Use this variable in inherited-classes
   * for accessing the layout binding
   */
  protected var binding: B? = null

  /**
   * For Injecting Base View Model
   */
  private var viewModel: VM? = null

  /**
   * Used for registering layout with activity
   */
  @LayoutRes
  abstract fun getLayoutId(): Int

  /**
   * Used for registering viewModel with activity
   */
  abstract fun getViewModel(): VM

  // For Progress Dialog
  private var progressDialog: LoadingDialog? = null

  /**
   * When Activity is Created
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize all bindings here
    this.initializeBinding()
  }

  /**
   * Binding Initialization
   */
  @Suppress("UNCHECKED_CAST")
  private fun initializeBinding() {

    // View DataBinding Init
    this.binding = DataBindingUtil.setContentView(this, getLayoutId())

    // ViewModel Init
    this.viewModel = viewModel ?: getViewModel()
    this.viewModel?.onAttach(this as? V)

    // DataBinding
    this.binding?.setVariable(BR.viewModel, this.viewModel)
  }

  /**
   * Hide Keyboard
   */
  protected fun hideKeyboard() {
    val view = this.currentFocus

    if (view != null) {
      val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

      inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  /**
   * Show Progress Dialog
   * @param message Message to display in loading dialog
   */
  protected fun showLoading(message: String? = "") {
    this.progressDialog = this.showProgressDialog(message)
  }

  /**
   * Hide Progress Dialog
   */
  protected fun hideLoading() {

    if (this.progressDialog != null && this.progressDialog?.isShowing == true) {
      progressDialog?.cancel()
    }
  }

  /**
   * On Activity Pause
   */
  override fun onPause() {

    // Hide Keyboard
    this.hideKeyboard()

    // Call Activity Pause
    super.onPause()
  }

  /**
   * On Activity Stop
   * Remove progressDialog instance to avoid memory-leak
   */
  override fun onStop() {
    if (progressDialog != null) {
      progressDialog?.dismiss()
    }
    super.onStop()
  }

  /**
   * On Activity Destroy
   * Call ViewModel.onDestroy() to detach View from ViewModel
   */
  override fun onDestroy() {
    viewModel?.onDetach()
    super.onDestroy()
  }

  /**
   * Progress Dialog Indeterminate
   * @param message Message to display in loading dialog
   */
  private fun showProgressDialog(message: String?): LoadingDialog {

    // Create a dialog
    val progressDialog = LoadingDialog(this, message)
    progressDialog.show()
    return progressDialog
  }

  /**
   * Show snack-bar
   * @param message Message to display in snackBar
   * @param isDisplayTimeLong LENGTH_LONG / LENGTH_SHORT
   */
  override fun showSnackBar(message: String?, isDisplayTimeLong: Boolean) {
    binding?.root?.let {
      if (isDisplayTimeLong) {
        Snackbar.make(it, message.toString(), Snackbar.LENGTH_LONG).show()
      } else {
        Snackbar.make(it, message.toString(), Snackbar.LENGTH_SHORT).show()
      }
    }
  }

  /**
   * Show toast
   * @param message Message to display in toast
   * @param isDisplayTimeLong LENGTH_LONG / LENGTH_SHORT
   */
  override fun showToast(message: String?, isDisplayTimeLong: Boolean) {
    if (isDisplayTimeLong) {
      Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
    } else {
      Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
    }
  }

  /**
   * Show Loading Dialog
   * @param show true/false
   * @param message Message to display in dialog
   */
  override fun showLoadingDialog(show: Boolean, message: String?) {
    if (show) {
      this.showProgressDialog(message)
    } else {
      this.hideLoading()
    }
  }
}

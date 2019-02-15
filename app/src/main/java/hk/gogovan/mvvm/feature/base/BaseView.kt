package hk.gogovan.mvvm.feature.base

/**
 * BaseView for initialization and configuration
 * It helps to communicate from ViewModel to Activity
 */
interface BaseView {

  /**
   * Show SnackBar from ViewModel to Activity
   */
  fun showSnackBar(
    message: String?,
    isDisplayTimeLong: Boolean = false
  )

  /**
   * Show Toast from ViewModel to Activity
   */
  fun showToast(
    message: String?,
    isDisplayTimeLong: Boolean = false
  )

  /**
   * Show Loading Dialog from ViewModel to Activity
   */
  fun showLoadingDialog(
    show: Boolean,
    message: String? = ""
  )
}

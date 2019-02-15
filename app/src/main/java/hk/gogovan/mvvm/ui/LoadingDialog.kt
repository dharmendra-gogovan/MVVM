@file:Suppress("DEPRECATION")

package hk.gogovan.mvvm.ui

import android.app.ProgressDialog
import android.content.Context

/**
 * Custom Loading Dialog UI Blocker
 */
class LoadingDialog constructor(context: Context?) : ProgressDialog(context) {

  // For Passing Custom Message
  constructor(context: Context?, message: String? = "Loading...") : this(context) {
    this.setMessage(message)
  }

  // Default Configurations
  init {
    this.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER)
    this.isIndeterminate = true
    this.setCancelable(false)
    this.setCanceledOnTouchOutside(false)
  }
}

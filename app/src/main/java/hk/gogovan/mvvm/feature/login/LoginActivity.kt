package hk.gogovan.mvvm.feature.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import hk.gogovan.mvvm.R
import hk.gogovan.mvvm.api.response.login.LoginResponse
import hk.gogovan.mvvm.databinding.ActivityLoginBinding
import hk.gogovan.mvvm.feature.base.BaseActivity
import hk.gogovan.mvvm.utils.StringUtils
import javax.inject.Inject

/**
 * Login Activity(Splash Screen)
 */
class LoginActivity : BaseActivity<LoginView, LoginViewModel, ActivityLoginBinding>(), LoginView {

  /**
   * Get ViewModel Factory From DI
   */
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun getLayoutId(): Int {
    return R.layout.activity_login
  }

  override fun getViewModel(): LoginViewModel {
    return ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize Views
    this.initializeViews()
  }

  private fun initializeViews() {
    binding?.loginButton?.setOnClickListener {
      sendLoginRequest()
    }
  }

  private fun sendLoginRequest() {
    val email = getViewModel().email.value
    val password = getViewModel().password.value

    if (StringUtils.isValidEmail(email) == false) {
      binding?.loginUserId?.error = "Invalid Email"
      return
    }
    if (StringUtils.isNullOrEmpty(password)) {
      binding?.loginUserPassword?.error = "Invalid Password"
      return
    }

    // Send Login Request
    showLoading("Authenticating...")
    getViewModel().sendLoginRequest()
  }

  override fun onLoginSuccess(loginResponse: LoginResponse?) {
    hideLoading()
    showSnackBar("Token: ${loginResponse?.token}", true)
  }

  override fun onLoginFailed() {
    hideLoading()
    binding?.loginUserPassword?.error = "Invalid Email/Password"
  }

}

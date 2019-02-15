package hk.gogovan.mvvm.feature.login

import hk.gogovan.mvvm.api.response.login.LoginResponse
import hk.gogovan.mvvm.feature.base.BaseView

/**
 * Login View Interface
 */
interface LoginView : BaseView {

  // On login successful
  fun onLoginSuccess(loginResponse: LoginResponse?)

  // On login failed
  fun onLoginFailed()
}
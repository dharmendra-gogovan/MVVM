package hk.gogovan.mvvm.feature.login

import androidx.lifecycle.MutableLiveData
import hk.gogovan.mvvm.api.ApiService
import hk.gogovan.mvvm.api.request.login.LoginRequest
import hk.gogovan.mvvm.feature.base.BaseViewModel
import hk.gogovan.mvvm.utils.BigSingleOperation
import javax.inject.Inject

/**
 * Login View Model
 */
class LoginViewModel @Inject constructor() : BaseViewModel<LoginView>() {

  @Inject lateinit var apiService: ApiService

  val email: MutableLiveData<String> = MutableLiveData()
  val password: MutableLiveData<String> = MutableLiveData()

  /**
   * Create a login request
   * Call view methods for login response success/failed
   */
  fun sendLoginRequest() {
    val request = LoginRequest(email.value, password.value)
    compositeDisposable?.add(
      apiService.postLogin(request)
        .compose(BigSingleOperation())
        .subscribe({
          // Pass data to the view
          view?.onLoginSuccess(it)

        }, {
          view?.showLoadingDialog(false)

          // Call view method in case of failed authentication
          view?.onLoginFailed()
          view?.showSnackBar(it.message, true)
        })
    )
  }

}

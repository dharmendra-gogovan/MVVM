package hk.gogovan.mvvm.api.request.login

/**
 * Login Request using email and password
 */
data class LoginRequest(
  val email: String?,
  val password: String?
)
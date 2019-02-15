package hk.gogovan.mvvm.api

import hk.gogovan.mvvm.api.request.login.LoginRequest
import hk.gogovan.mvvm.api.response.login.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * All API Endpoints are registered here
 */
interface ApiService {

  // Login Request
  @POST("login")
  fun postLogin(@Body loginRequest: LoginRequest): Single<LoginResponse>

}

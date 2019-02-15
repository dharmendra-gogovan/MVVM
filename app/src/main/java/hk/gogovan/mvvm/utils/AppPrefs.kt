package hk.gogovan.mvvm.utils

import android.content.Context
import net.grandcentrix.tray.AppPreferences
import javax.inject.Singleton

/**
 * Shared preference manager for local user credentials
 */
@Singleton
class AppPrefs(context: Context) {

  private val appPreferences: AppPreferences = AppPreferences(context)

  // Discard return value Hack
  @Suppress("unused")
  private fun Any?.discard() = Unit

  // User Token
  var userToken
    get() = appPreferences.getString("SESSION_USER_TOKEN", "")
    set(token) = appPreferences.put("SESSION_USER_TOKEN", token).discard()

  fun clearAllData() {
    appPreferences.clear()
  }

}
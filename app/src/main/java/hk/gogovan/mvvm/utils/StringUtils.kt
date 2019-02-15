package hk.gogovan.mvvm.utils

import android.util.Patterns

/**
 * Utility for string manipulation
 */
class StringUtils {

  /**
   * Kotlin companion object look like static in Java,
   * at runtime those are still instance members of real objects,
   * and can, for example, implement interfaces.
   */
  companion object {

    fun isNullOrEmpty(message: String?): Boolean {
      return !(message != null && !message.isEmpty())
    }

    fun isValidEmail(email: String?): Boolean? {
      return email?.isNotBlank() == true && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
  }
}

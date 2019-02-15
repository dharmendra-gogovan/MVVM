package hk.gogovan.mvvm

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import hk.gogovan.mvvm.dagger.component.DaggerAppComponent
import timber.log.Timber

/**
 * Main application class for base configuration
 * Here we configure our dagger injection
 * or any application level dependency
 */
class MainApplication : DaggerApplication() {

  /**
   * Injecting Timber logging in Application onCreate()
   */
  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  /**
   * Inject Dagger App Component after onCreate()
   */
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    val component = DaggerAppComponent.builder()
        .application(this)
        .build()
    component.inject(this)
    return component
  }

}
package hk.gogovan.mvvm.dagger.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import hk.gogovan.mvvm.utils.AppPrefs
import javax.inject.Singleton

/**
 * App Module to provide App-Level Dependencies
 */
@Module(
    includes = [
      ViewModelModule::class
    ]
)
class AppModule {

  @Provides
  @Singleton
  fun providesContext(application: Application): Context {
    return application
  }

  /**
   * AppPrefs Shared Preferences
   * @param context Context
   * @return {AppPrefs} Returns AppPrefs
   */
  @Provides
  @Singleton
  fun providesAppPrefs(context: Context): AppPrefs {
    return AppPrefs(context)
  }

}
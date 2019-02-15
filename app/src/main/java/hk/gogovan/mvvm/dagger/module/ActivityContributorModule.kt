package hk.gogovan.mvvm.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hk.gogovan.mvvm.feature.login.LoginActivity

/**
 * Activity Contributor for publishing all activities
 */
@Module
abstract class ActivityContributorModule {

  @ContributesAndroidInjector
  abstract fun contributeLoginActivity(): LoginActivity

}
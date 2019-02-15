package hk.gogovan.mvvm.dagger.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import hk.gogovan.mvvm.MainApplication
import hk.gogovan.mvvm.dagger.module.ActivityContributorModule
import hk.gogovan.mvvm.dagger.module.AppModule
import hk.gogovan.mvvm.dagger.module.NetworkModule
import javax.inject.Singleton

/**
 * App Component for Generating DaggerAppComponent
 */
@Singleton
@Component(
    modules = [
      AndroidSupportInjectionModule::class,
      AppModule::class,
      ActivityContributorModule::class,
      NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

  fun inject(application: MainApplication)

  override fun inject(instance: DaggerApplication?)

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent

  }
}
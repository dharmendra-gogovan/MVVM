package hk.gogovan.mvvm.dagger.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import hk.gogovan.mvvm.dagger.ViewModelFactory

/**
 * ViewModel Module to register ViewModels
 */
@Module
abstract class ViewModelModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
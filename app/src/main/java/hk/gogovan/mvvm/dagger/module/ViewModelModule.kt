package hk.gogovan.mvvm.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hk.gogovan.mvvm.dagger.ViewModelFactory
import hk.gogovan.mvvm.dagger.ViewModelKey
import hk.gogovan.mvvm.feature.login.LoginViewModel

/**
 * ViewModel Module to register ViewModels
 */
@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(LoginViewModel::class)
  abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

  /**
   * ViewModel Factory Binder
   */
  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
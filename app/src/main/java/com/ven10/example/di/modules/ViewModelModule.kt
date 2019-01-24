package com.ven10.example.di.modules

import androidx.lifecycle.ViewModel
import com.ven10.example.di.mapkeys.ViewModelKey
import com.ven10.example.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMyViewModel(myViewModel: HomeViewModel): ViewModel
}
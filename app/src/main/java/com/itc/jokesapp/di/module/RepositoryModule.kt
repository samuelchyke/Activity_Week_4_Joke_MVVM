package com.itc.jokesapp.di.module

import androidx.lifecycle.ViewModel
import com.itc.jokesapp.repository.JokesRepo
import com.itc.jokesapp.repository.JokesRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideJokesRepository(jokesRepoImpl: JokesRepoImpl): JokesRepo

}
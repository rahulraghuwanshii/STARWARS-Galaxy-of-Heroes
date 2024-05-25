package com.rahulraghuwanshi.starwarshero.di.core.module

import android.content.Context
import com.rahulraghuwanshi.starwarshero.di.characters_list.CharacterListSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [CharacterListSubComponent::class, CharacterListSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}
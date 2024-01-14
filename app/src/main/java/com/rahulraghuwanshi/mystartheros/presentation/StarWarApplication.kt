package com.rahulraghuwanshi.mystartheros.presentation

import android.app.Application
import com.rahulraghuwanshi.mystartheros.di.character_detail.CharacterDetailSubComponent
import com.rahulraghuwanshi.mystartheros.di.characters_list.CharacterListSubComponent
import com.rahulraghuwanshi.mystartheros.di.core.Injector
import com.rahulraghuwanshi.mystartheros.di.core.component.AppComponent
import com.rahulraghuwanshi.mystartheros.di.core.component.DaggerAppComponent
import com.rahulraghuwanshi.mystartheros.di.core.module.AppModule

/**
 * we use our AppComponent (now prefixed with Dagger)
 * to inject our Application class.
 * This way a DispatchingAndroidInjector is injected which is
 * then returned when an injector for an activity is requested.
 * */
class StarWarApplication : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

    override fun createCharacterDetailSubComponent(): CharacterDetailSubComponent {
        return appComponent.characterDetailSubComponent().create()
    }

    override fun createCharacterListSubComponent(): CharacterListSubComponent {
        return appComponent.characterListSubComponent().create()
    }
}
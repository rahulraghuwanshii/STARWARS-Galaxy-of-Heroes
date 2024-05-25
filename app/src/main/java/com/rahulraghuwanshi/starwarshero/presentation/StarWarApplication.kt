package com.rahulraghuwanshi.starwarshero.presentation

import android.app.Application
import com.rahulraghuwanshi.starwarshero.di.character_detail.CharacterDetailSubComponent
import com.rahulraghuwanshi.starwarshero.di.characters_list.CharacterListSubComponent
import com.rahulraghuwanshi.starwarshero.di.core.Injector
import com.rahulraghuwanshi.starwarshero.di.core.component.AppComponent
import com.rahulraghuwanshi.starwarshero.di.core.component.DaggerAppComponent
import com.rahulraghuwanshi.starwarshero.di.core.module.AppModule

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
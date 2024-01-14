package com.rahulraghuwanshi.mystartheros.di.core.component

import com.rahulraghuwanshi.mystartheros.di.character_detail.CharacterDetailSubComponent
import com.rahulraghuwanshi.mystartheros.di.characters_list.CharacterListSubComponent
import com.rahulraghuwanshi.mystartheros.di.core.module.ApiModule
import com.rahulraghuwanshi.mystartheros.di.core.module.AppModule
import com.rahulraghuwanshi.mystartheros.di.core.module.DbModule
import com.rahulraghuwanshi.mystartheros.di.core.module.RemoteDataModule
import com.rahulraghuwanshi.mystartheros.di.core.module.RepositoryModule
import com.rahulraghuwanshi.mystartheros.di.core.module.UseCaseModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * We mark this interface with the @Component annotation.
 * And we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 * */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        DbModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent {

    fun characterDetailSubComponent(): CharacterDetailSubComponent.Factory
    fun characterListSubComponent(): CharacterListSubComponent.Factory
}

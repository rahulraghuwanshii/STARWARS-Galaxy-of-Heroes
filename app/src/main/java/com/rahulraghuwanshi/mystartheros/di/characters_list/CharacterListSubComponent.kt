package com.rahulraghuwanshi.mystartheros.di.characters_list

import com.rahulraghuwanshi.mystartheros.presentation.characters_list.CharacterFragment
import dagger.Subcomponent

@CharacterListScope
@Subcomponent(modules = [CharacterListModule::class])
interface CharacterListSubComponent {
    fun inject(characterFragment: CharacterFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): CharacterListSubComponent
    }

}

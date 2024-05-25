package com.rahulraghuwanshi.starwarshero.di.character_detail

import com.rahulraghuwanshi.starwarshero.presentation.character_detail.CharacterDetailFragment
import dagger.Subcomponent

@CharacterDetailScope
@Subcomponent(modules = [CharacterDetailModule::class])
interface CharacterDetailSubComponent {
    fun inject(characterDetailFragment: CharacterDetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): CharacterDetailSubComponent
    }

}

package com.rahulraghuwanshi.starwarshero.di.core

import com.rahulraghuwanshi.starwarshero.di.character_detail.CharacterDetailSubComponent
import com.rahulraghuwanshi.starwarshero.di.characters_list.CharacterListSubComponent

interface Injector {
    fun createCharacterDetailSubComponent(): CharacterDetailSubComponent
    fun createCharacterListSubComponent(): CharacterListSubComponent
}
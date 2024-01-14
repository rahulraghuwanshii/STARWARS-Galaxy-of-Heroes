package com.rahulraghuwanshi.mystartheros.di.core

import com.rahulraghuwanshi.mystartheros.di.character_detail.CharacterDetailSubComponent
import com.rahulraghuwanshi.mystartheros.di.characters_list.CharacterListSubComponent

interface Injector {
    fun createCharacterDetailSubComponent(): CharacterDetailSubComponent
    fun createCharacterListSubComponent(): CharacterListSubComponent
}
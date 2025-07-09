package com.example.androidbasics.di

import com.example.androidbasics.ui.NoteDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::NoteDetailViewModel)
}
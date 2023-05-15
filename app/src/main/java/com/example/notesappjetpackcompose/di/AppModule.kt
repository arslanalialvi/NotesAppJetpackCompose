package com.example.notesappjetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.example.notesappjetpackcompose.data.NotesDao
import com.example.notesappjetpackcompose.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesNotesDao(notesDatabase: NotesDatabase):NotesDao= notesDatabase.getNoteDao()


    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase=
    Room.databaseBuilder(context,NotesDatabase::class.java,"notes_db").fallbackToDestructiveMigration().build()
}
package com.example.remindme.di

import android.content.Context
import androidx.room.Room
import com.example.remindme.reminder.data.local.ReminderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideReminderDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(
        context = appContext,
        klass = ReminderDatabase::class.java,
        name = "reminder_db"
    ).build()

    @Provides
    @Singleton
    fun provideReminderDao(db: ReminderDatabase) = db.reminderDao()
}
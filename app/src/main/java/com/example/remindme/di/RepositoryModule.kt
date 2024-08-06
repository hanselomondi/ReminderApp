package com.example.remindme.di

import com.example.remindme.reminder.data.repository.ReminderRepositoryImpl
import com.example.remindme.reminder.domain.repository.ReminderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideReminderRepository(
        reminderRepositoryImpl: ReminderRepositoryImpl
    ): ReminderRepository
}
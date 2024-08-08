package com.example.remindme.reminder.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.remindme.reminder.domain.model.ReminderPriority
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @Query("SELECT * FROM reminder_table")
    fun getAllReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminder_table WHERE id = :id")
    suspend fun getReminderById(id: Int): ReminderEntity?

    @Query("SELECT * FROM reminder_table ORDER BY dueDate ASC")
    fun getRemindersSortedByDate(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminder_table ORDER BY createdAt ASC")
    fun getRemindersSortedByDateCreated(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminder_table WHERE isCompleted = 0")
    fun getInCompleteReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminder_table WHERE isCompleted = 1")
    fun getCompletedReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminder_table WHERE priority = :priority")
    fun getRemindersByPriority(priority: ReminderPriority): Flow<List<ReminderEntity>>

    @Query("UPDATE reminder_table SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateReminderStatus(id: Int, isCompleted: Boolean)
}
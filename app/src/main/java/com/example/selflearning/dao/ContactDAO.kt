package com.example.selflearning.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.selflearning.entity.Contact

@Dao
interface ContactDAO {
    @Insert
    suspend fun insert(contact: Contact)
    @Update
    suspend fun update(contact: Contact)
    @Delete
    suspend fun delete(contact: Contact)
    @Query("select * from contact")
    fun getContacts():LiveData<List<Contact>>
}
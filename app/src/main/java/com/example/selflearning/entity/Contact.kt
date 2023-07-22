package com.example.selflearning.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    val name:String,
    val phone:String,
    val date:Date,

    val isActive:Int  // new column
)

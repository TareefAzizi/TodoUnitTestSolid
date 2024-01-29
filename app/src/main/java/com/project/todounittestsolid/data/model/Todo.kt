package com.project.todounittestsolid.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val desc:String,
): Parcelable

package com.example.myapplication.database.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.database.ImageConverter

@Entity(
    tableName = "cards",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.RESTRICT
        )
    ]
)
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "mileage")
    val mileage: Int,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "image")
    val image: Bitmap,
    @ColumnInfo(name="user_id")
    val userId: Int
){
    override fun hashCode(): Int {
        return id ?: -1
    }
}
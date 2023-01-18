package com.example.demo_fragmentandretrofit.roomDB


import androidx.annotation.NonNull
import androidx.room.*
import com.example.demo_fragmentandretrofit.roomDB.RoomEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class RoomEntity {
    companion object{
        const val TABLE_NAME="wah"
    }

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var name : String =""

    @NonNull
    @ColumnInfo(name = "wah", typeAffinity = ColumnInfo.TEXT)
    var time ="takotime"

    @NonNull
    @ColumnInfo(name = "tako", typeAffinity = ColumnInfo.TEXT)
    var test ="tako"

    //不存入DB
    @Ignore
    var ignoreText=""
}
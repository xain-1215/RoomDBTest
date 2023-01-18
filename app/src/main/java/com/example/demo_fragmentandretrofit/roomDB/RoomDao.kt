package com.example.demo_fragmentandretrofit.roomDB

import androidx.room.*


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item:RoomEntity):Long

    @Insert
    fun insertAll(item: RoomEntity)

    @Query("SELECT * " +
            "FROM wah " +
            "WHERE name LIKE :name")
    fun findByName(name:String?):RoomEntity

    @Query("SELECT * " +
            "FROM wah")
    fun getAll() : List<RoomEntity>

    @Delete
    fun delete(item:RoomEntity)

    @Update
    fun update(item:RoomEntity)
}
package com.example.arthub.ArtHub_Tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ARTHUB_ENTRY_DAO {
    // CREATE
    @Insert
    fun create_new_entry(entry : Arthub_ENTRY)

    // READ
    @Query("SELECT * FROM Arthub_ENTRY")
    fun get_all_entrys(): List<Arthub_ENTRY>

    @Query("SELECT * FROM Arthub_ENTRY WHERE entry_id = :selected_entry_id")
    fun get_entry_by_id(selected_entry_id: Int): Arthub_ENTRY

    // UPDATE
    @Update
    fun update_entry(entry_ref: Arthub_ENTRY)

    // DELETE
    @Delete
    fun delete_entry(entry_ref: Arthub_ENTRY)
}

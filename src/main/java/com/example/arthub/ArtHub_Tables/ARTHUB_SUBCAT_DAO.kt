package com.example.arthub.ArtHub_Tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ARTHUB_SUBCAT_DAO {
    // CREATE
    @Insert
    fun create_new_subcat(subcat : Arthub_SUBCAT)

    // READ
    @Query("SELECT * FROM Arthub_SUBCAT")
    fun get_all_subcats(): List<Arthub_SUBCAT>

    @Query("SELECT * FROM Arthub_SUBCAT WHERE subcat_id = :selected_subcat_id")
    fun get_subcat_by_id(selected_subcat_id: Int): Arthub_SUBCAT

    // UPDATE
    @Update
    fun update_subcat(subcat_ref: Arthub_SUBCAT)

    // DELETE
    @Delete
    fun delete_subcat(subcat_ref: Arthub_SUBCAT)
}
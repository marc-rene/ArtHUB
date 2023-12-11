package com.example.arthub.ArtHub_Tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
public interface ARTHUB_PROJECT_DAO {
    // CREATE
    @Insert
    fun create_new_project(project : Arthub_PROJECT)

    // READ
    @Query("SELECT * FROM Arthub_PROJECT")
    fun get_all_projects(): List<Arthub_PROJECT>

    @Query("SELECT * FROM Arthub_PROJECT WHERE project_id = :selected_project_id")
    fun get_project_by_id(selected_project_id: Int): Arthub_PROJECT

    // UPDATE
    @Update
    fun update_project(project_ref: Arthub_PROJECT)

    // DELETE
    @Delete
    fun delete_project(project_ref: Arthub_PROJECT)
}
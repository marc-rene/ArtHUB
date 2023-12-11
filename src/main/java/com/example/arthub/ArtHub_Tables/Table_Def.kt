package com.example.arthub.ArtHub_Tables

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date


enum class Arthub_Content_Type {
    PHOTO,
    VIDEO,
    AUDIO,
    URL
}


// PROJECT
@Entity
data class Arthub_PROJECT(
    @PrimaryKey val project_id: Int,
    @ColumnInfo(name = "project_title") val project_title: String,
    @ColumnInfo(name = "project_desc") val project_desc: String,
    @ColumnInfo(name = "date_created") val date_created: Long // YYYY-mm-dd
)


// SUBCATEGORY
@Entity(
// Explanation of foreign keys in Android ROOM : https://stackoverflow.com/questions/58518229/what-is-the-difference-between-foreignkey-and-relation-annotations-in-room-dat/67006942#67006942
    foreignKeys = [ForeignKey(
        entity = Arthub_PROJECT::class,
        parentColumns = arrayOf("project_id"),
        childColumns = arrayOf("owning_project_id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Arthub_SUBCAT(
    @PrimaryKey val subcat_id: Int,
    @ColumnInfo(name = "owning_subcat_id") val owning_subcat_id: Int,
    @ColumnInfo(name = "owning_project_id") val owning_project_id: Int,
    @ColumnInfo(name = "subcat_title") val subcat_title: String?,
)

// ENTRY
@Entity(
    foreignKeys = [ForeignKey(
        entity = Arthub_SUBCAT::class,
        parentColumns = arrayOf("subcat_id"),
        childColumns = arrayOf("owning_subcat_id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Arthub_ENTRY(
    @PrimaryKey val entry_id: Int,
    @ColumnInfo(name = "owning_subcat_id") val owning_subcat_id: Int,
    @ColumnInfo(name = "entry_title") val entry_title: String,
    @ColumnInfo(name = "entry_desc") val entry_desc: String,
    @ColumnInfo(name = "entry_type") val entry_type: Arthub_Content_Type,
    @ColumnInfo(name = "content_url") val content_url: String?,
    @ColumnInfo(name = "geo_co_ords_x") val geo_co_ords_x: Float,
    @ColumnInfo(name = "geo_co_ords_y") val geo_co_ords_y: Float
)


data class Projects_and_Subcats(
    @Embedded val the_project: Arthub_PROJECT,
    @Relation(
        parentColumn = "project_id",
        entityColumn = "owning_project_id"
    )
    val subcats: List<Arthub_SUBCAT>
)

data class Subcats_and_entries(
    @Embedded val the_subcat: Arthub_SUBCAT,
    @Relation(
        parentColumn = "subcat_id",
        entityColumn = "owning_subcat_id"
    )
    val entries: List<Arthub_ENTRY>
)






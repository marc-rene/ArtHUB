package com.example.arthub.ArtHub_Tables

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    entities = [Arthub_PROJECT::class, Arthub_SUBCAT::class, Arthub_ENTRY::class],
    version = 1
)

//https://gorillalogic.com/blog/android-room-tutorial-simplifying-how-you-work-with-app-data
abstract class Arthub_DB : RoomDatabase() {
    abstract fun project_dao(): ARTHUB_PROJECT_DAO
    abstract fun subcat_dao(): ARTHUB_SUBCAT_DAO
    abstract fun entry_dao(): ARTHUB_ENTRY_DAO

    private class Arthub_DB_Callback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { Arthub_DB ->
                scope.launch {
                    var project_dao = Arthub_DB.project_dao()


                    project_dao.create_new_project(Arthub_PROJECT(1, "YAY", "THIS WORKS", 123456))


                }
            }
        }
    }



    companion object {
        @Volatile
        var INSTANCE: Arthub_DB? = null

        fun Get_Arthub_DB(context: Context, scope: CoroutineScope) {
            if (INSTANCE == null) {
                synchronized(Arthub_DB::class)
                {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        Arthub_DB::class.java,
                        "Arthub_DB"
                    )
                        .addCallback(Arthub_DB_Callback(scope))
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }

                fun destroyDataBase() {
                    INSTANCE = null
                }
            }

        }


    }
}

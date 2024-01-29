package com.project.todounittestsolid.data.db.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.todounittestsolid.data.model.Todo

@Database(entities = [Todo::class], version = 3)
abstract class TodosDatabase : RoomDatabase() {

    abstract val dao: TodosDao

    companion object {
        const val name = "todo_database"

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("""
                    CREATE TABLE temp_todo (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, desc TEXT NOT NULL)
                """.trimIndent())
                database.execSQL("""
                    INSERT INTO temp_todo (id, title, desc) SELECT id, title, desc FROM todo
                """.trimIndent())
                database.execSQL("""
                    DROP TABLE todo
                """.trimIndent())
                database.execSQL("""
                    ALTER TABLE temp_todo RENAME TO todo
                """.trimIndent())
            }
        }
    }
}

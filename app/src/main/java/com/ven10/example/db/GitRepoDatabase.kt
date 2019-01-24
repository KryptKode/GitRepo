package com.ven10.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ven10.example.model.GitRepo

/**
 * Database schema that holds the list of repos.
 */
@Database(
        entities = [GitRepo::class],
        version = 1,
        exportSchema = false
)
abstract class GitRepoDatabase : RoomDatabase() {

    abstract fun reposDao(): GitRepoDao

}

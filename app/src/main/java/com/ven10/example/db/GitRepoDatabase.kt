package com.ven10.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ven10.example.db.converter.GitUserConverter
import com.ven10.example.model.GitRepo

/**
 * Database schema that holds the list of repos.
 */
@Database(
        entities = [GitRepo::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(GitUserConverter::class)
abstract class GitRepoDatabase : RoomDatabase() {

    abstract fun reposDao(): GitRepoDao

}

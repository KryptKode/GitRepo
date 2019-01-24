package com.ven10.example.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ven10.example.model.GitRepo


/**
 * Room data access object for accessing the [GitRepo] table.
 */
@Dao
interface GitRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<GitRepo>)

    @Query("SELECT * FROM repos ORDER BY stars DESC, name ASC")
    fun getRepos(): DataSource.Factory<Int,GitRepo>
}

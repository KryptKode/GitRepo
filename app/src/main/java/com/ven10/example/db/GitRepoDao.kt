package com.ven10.example.db

import androidx.paging.DataSource
import androidx.room.*
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

    @Query("DELETE FROM repos")
    fun deleteAll()
}

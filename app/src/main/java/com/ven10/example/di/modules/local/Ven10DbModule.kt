package com.ven10.example.di.modules.local


import android.content.Context
import androidx.room.Room
import com.ven10.example.db.GitRepoDao
import com.ven10.example.db.GitRepoDatabase
import com.ven10.example.db.GitRepoLocal
import com.ven10.example.di.scopes.Ven10AppScope
import com.ven10.example.utils.Constants
import dagger.Module
import dagger.Provides

@Module
class Ven10DbModule {

    @Provides
    @Ven10AppScope
    fun provideGitRepoDatabase(context: Context): GitRepoDatabase{
        val builder = Room.databaseBuilder(context, GitRepoDatabase::class.java, Constants.DATABASE_NAME)
        return builder.build()
    }

    @Provides
    @Ven10AppScope
    fun provideGitRepoDAO(gitRepoDatabase: GitRepoDatabase): GitRepoDao{
        return gitRepoDatabase.reposDao()
    }

    @Provides
    @Ven10AppScope
    fun provideGitRepoLocal(repoDao: GitRepoDao): GitRepoLocal {
        return GitRepoLocal(repoDao)
    }
}
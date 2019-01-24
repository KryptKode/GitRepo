package com.ven10.example.db

import androidx.paging.DataSource
import com.ven10.example.model.GitRepo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitRepoLocal @Inject constructor(var repoDao: GitRepoDao) {

    fun insertRepos(repos: List<GitRepo>): Observable<Unit>? {
        return Observable.fromCallable {repoDao.insert(repos)}
            .subscribeOn(Schedulers.io())
    }

    fun getRepos(): DataSource.Factory<Int, GitRepo> {
        return repoDao.getRepos()
    }
}

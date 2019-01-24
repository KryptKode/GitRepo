
package com.ven10.example.data

import androidx.paging.LivePagedListBuilder
import android.util.Log
import com.ven10.example.api.GithubService
import com.ven10.example.db.GitRepoLocal
import com.ven10.example.model.GitRepoSearchResult
import timber.log.Timber
import javax.inject.Inject


/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository @Inject constructor(
    var service: GithubService,
    var cache: GitRepoLocal
) {

    /**
     * Search repositories whose names match the query.
     */
    fun getRepos(): GitRepoSearchResult {
        Timber.d("Getting trending repos")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.getRepos()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = GitRepoBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return GitRepoSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}

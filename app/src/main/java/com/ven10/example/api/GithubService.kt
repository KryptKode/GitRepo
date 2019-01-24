
package com.ven10.example.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Github API communication setup via Retrofit.
 */
interface GithubService {
    /**
     * Get android repos ordered by stars to get the trending repos
     */
    @GET("search/repositories?q=android&sort=stars")
    fun searchRepos(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Observable<SearchResponse>

}
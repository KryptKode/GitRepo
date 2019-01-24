package com.ven10.example.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.Flowable

/**
 * RepoSearchResult from a search, which contains LiveData<List<Repo>> holding query data,
 * and a LiveData<String> of network error state.
 */
data class GitRepoSearchResult(
    val data: LiveData<PagedList<GitRepo>>,
    val networkErrors: LiveData<String>
)

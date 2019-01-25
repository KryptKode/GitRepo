package com.ven10.example.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ven10.example.utils.NetworkState


data class GitRepoSearchResult(
    val data: LiveData<PagedList<GitRepo>>,
    val networkErrors: LiveData<NetworkState>,
    val refresh : () -> Unit
)

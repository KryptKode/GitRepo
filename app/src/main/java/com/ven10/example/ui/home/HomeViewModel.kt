package com.ven10.example.ui.home

import androidx.lifecycle.ViewModel
import com.ven10.example.data.GithubRepository
import com.ven10.example.model.GitRepoSearchResult
import javax.inject.Inject

class HomeViewModel @Inject constructor(var repository: GithubRepository):ViewModel(){

    fun getTrendingRepos(): GitRepoSearchResult {
        return repository.getRepos()
    }

}

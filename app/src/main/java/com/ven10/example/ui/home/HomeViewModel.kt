package com.ven10.example.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ven10.example.data.GithubRepository
import com.ven10.example.model.GitRepoSearchResult
import javax.inject.Inject

class HomeViewModel @Inject constructor(var repository: GithubRepository):ViewModel(){


    val blank = MutableLiveData<String>()

    val repoResult = Transformations.map(blank){
        repository.getRepos()
    }

    val networkState = Transformations.switchMap(repoResult){
        it.networkErrors
    }

    val repoList = Transformations.switchMap(repoResult){
        it.data
    }





}

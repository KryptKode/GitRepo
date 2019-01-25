package com.ven10.example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ven10.example.api.GithubService
import com.ven10.example.db.GitRepoLocal
import com.ven10.example.model.GitRepo
import com.ven10.example.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data
 **/
class GitRepoBoundaryCallback @Inject constructor(
        var service: GithubService,
        var cache: GitRepoLocal
) : PagedList.BoundaryCallback<GitRepo>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    private var lastRequestedPage = 1

    val disposable = CompositeDisposable()


    val networkState = MutableLiveData<NetworkState>()

    override fun onZeroItemsLoaded() {
        Timber.d(" onZeroItemsLoaded")
        requestAndSaveData()
    }


    override fun onItemAtEndLoaded(itemAtEnd: GitRepo) {
        Timber.d(" onItemAtEndLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (networkState.value == NetworkState.LOADING) return

        networkState.postValue(NetworkState.LOADING)
        disposable.add(service.searchRepos(lastRequestedPage, NETWORK_PAGE_SIZE)
                .flatMap { cache.insertRepos(it.items) }
                .subscribeOn(Schedulers.newThread()).subscribe({
                    lastRequestedPage++
                    networkState.postValue(NetworkState.LOADED)
                }, {

                    networkState.postValue(NetworkState.error(it.message))
                }, {
                    networkState.postValue(NetworkState.LOADED)
                })
        )
    }
}
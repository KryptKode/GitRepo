package com.ven10.example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ven10.example.api.GithubService
import com.ven10.example.db.GitRepoLocal
import com.ven10.example.model.GitRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data
 **/
class GitRepoBoundaryCallback(
    @Inject val service: GithubService,
    @Inject val cache: GitRepoLocal
) : PagedList.BoundaryCallback<GitRepo>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    private var lastRequestedPage = 1

    val disposable = CompositeDisposable()
    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private var isQueryInProgress = false


    override fun onZeroItemsLoaded() {
        Timber.d(" onZeroItemsLoaded")
        requestAndSaveData()
    }


    override fun onItemAtEndLoaded(itemAtEnd: GitRepo) {
        Timber.d(" onItemAtEndLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isQueryInProgress) return
        isQueryInProgress = true
        disposable.add(service.searchRepos(lastRequestedPage, NETWORK_PAGE_SIZE)
            .flatMap { cache.insertRepos(it.items) }
            .subscribeOn(Schedulers.newThread()).subscribe({
                lastRequestedPage++
                isQueryInProgress = false
            }, {
                _networkErrors.postValue(it.message)
                isQueryInProgress = false
            }, {
                isQueryInProgress = false
            })
        )
    }
}
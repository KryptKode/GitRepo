package com.ven10.example.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.ven10.example.databinding.ItemRepoBinding
import com.ven10.example.model.GitRepo
import timber.log.Timber

class HomeAdapter (private val listener: HomeItemListener): PagedListAdapter<GitRepo, HomeViewHolder>(GitRepo.ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val repo = getItem(position)
        Timber.d("Got item %s", repo)
        if (repo != null) {
            Timber.d("Showing item")
            holder.performBind(repo)
        }else{
            Timber.d("Showing placeholder")
            holder.showPlaceHolder()
        }
    }
}
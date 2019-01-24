package com.ven10.example.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.ven10.example.databinding.ItemRepoBinding
import com.ven10.example.model.GitRepo

class HomeAdapter (private val listener: HomeItemListener): PagedListAdapter<GitRepo, HomeViewHolder>(GitRepo.ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.performBind(repo)
        }
    }
}
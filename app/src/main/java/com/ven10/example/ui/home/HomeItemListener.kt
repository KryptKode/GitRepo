package com.ven10.example.ui.home

import com.ven10.example.model.GitRepo

interface HomeItemListener {
    fun onItemClick(item: GitRepo)
}
package com.ven10.example.ui.home

import android.view.View
import com.ven10.example.model.GitRepo
import com.ven10.example.views.CircleImageView

interface HomeItemListener {
    fun onItemClick(item: GitRepo, img: View)
}
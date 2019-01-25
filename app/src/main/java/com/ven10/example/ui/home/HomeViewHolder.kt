package com.ven10.example.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ven10.example.R
import com.ven10.example.databinding.ItemRepoBinding
import com.ven10.example.model.GitRepo
import com.ven10.example.utils.DateTimeUtils
import com.ven10.example.utils.GlideApp
import com.ven10.example.utils.NumberUtils

class HomeViewHolder(val binding: ItemRepoBinding, val listener: HomeItemListener) : RecyclerView.ViewHolder(binding.root){

    fun performBind(item: GitRepo){
        binding.baseLayout.visibility = View.VISIBLE
        binding.itemRepoPlaceholder.baseLayout.visibility = View.GONE
        if(binding.itemRepoPlaceholder.baseLayout.isShimmerStarted){
            binding.itemRepoPlaceholder.baseLayout.stopShimmer()
        }

        binding.tvName.text = item.name
        binding.tvUsername.text = item.user?.username
        binding.tvStars.text = NumberUtils.format(item.stars.toLong())
        binding.tvDate.text = DateTimeUtils.toTimeStampRelative(item.dateUpdated!!)

        GlideApp.with(binding.img)
                .load(item.user?.avatarUrl)
                .placeholder(R.drawable.ic_stream)
                .error(R.drawable.ic_full_image)
                .into(binding.img)

        binding.base.setOnClickListener {
            listener.onItemClick(item)
        }

        binding.baseLayout.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    fun showPlaceHolder(){
        binding.baseLayout.visibility = View.GONE
        binding.itemRepoPlaceholder.baseLayout.visibility = View.VISIBLE
        if(!binding.itemRepoPlaceholder.baseLayout.isShimmerStarted){
            binding.itemRepoPlaceholder.baseLayout.startShimmer()

        }

    }

}

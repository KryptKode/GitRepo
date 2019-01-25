package com.ven10.example.ui.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ven10.example.R
import com.ven10.example.databinding.FragmentDetailBinding
import com.ven10.example.model.GitRepo
import com.ven10.example.ui.base.BaseFragment
import com.ven10.example.utils.DateTimeUtils
import com.ven10.example.utils.GlideApp
import com.ven10.example.utils.SpannableHelper
import com.ven10.example.views.SwipeRefreshLayoutHelper
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.include_detail.view.*
import javax.inject.Inject

class DetailFragment @Inject constructor(): BaseFragment() {

    lateinit var binding: FragmentDetailBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container,false);
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SwipeRefreshLayoutHelper().init(binding.swipeRefreshLayout)
        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            activity?.recreate()
        }
        val repo = arguments?.getParcelable<GitRepo>("repo")

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = getString(R.string.repo_details)
        binding.collapsingToolbar.title = getString(R.string.repo_details)







        binding.tvUsername.text = repo?.user?.username

        binding.tvUrl.text = SpannableHelper().formatToLink(repo?.user?.url, context)
        binding.tvRepoUrl.text = SpannableHelper().formatToLink(repo?.url, context)

        binding.tvUrl.movementMethod = LinkMovementMethod.getInstance()
        binding.tvRepoUrl.movementMethod = LinkMovementMethod.getInstance()

        binding.tvName.text = repo?.name
        binding.tvDescription.text = repo?.description



        val dateCreated = "${DateTimeUtils.formatDate(repo?.dateCreated ?: "")}, ${getString(R.string.at)} ${DateTimeUtils.formatTime(repo?.dateCreated?: "")} "
        binding.tvCreated.text = dateCreated


        val dateUpdated = "${DateTimeUtils.formatDate(repo?.dateUpdated ?: "")}, ${getString(R.string.at)} ${DateTimeUtils.formatTime(repo?.dateUpdated ?: "")} "
        binding.tvUpdated.text = dateUpdated

        binding.includeDetail.tv_views.text = repo?.watchers.toString()
        binding.includeDetail.tv_stars.text = repo?.stars.toString()
        binding.includeDetail.tv_forks.text = repo?.forks.toString()





        GlideApp.with(binding.img)
                .load(repo?.user?.avatarUrl)
                .placeholder(R.drawable.ic_stream)
                .error(R.drawable.ic_full_image)
                .into(binding.img)



        GlideApp.with(binding.image)
                .load(repo?.user?.avatarUrl)
                .placeholder(R.drawable.ic_stream)
                .error(R.drawable.ic_full_image)
                .into(binding.image)


        binding.swipeRefreshLayout.isRefreshing = false
    }

}
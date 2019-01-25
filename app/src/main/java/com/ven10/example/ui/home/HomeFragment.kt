package com.ven10.example.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ven10.example.R
import com.ven10.example.databinding.FragmentHomeBinding
import com.ven10.example.model.GitRepo
import com.ven10.example.ui.base.BaseFragment
import com.ven10.example.utils.NetworkState
import com.ven10.example.views.ItemDivider
import com.ven10.example.views.SwipeRefreshLayoutHelper
import timber.log.Timber
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel

    private val listener = object : HomeItemListener {
        override fun onItemClick(item: GitRepo) {
            //TODO open detail activity
        }
    }

    private val adapter = HomeAdapter(listener)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProviders.of(this, viewModeFactory)
                .get(HomeViewModel::class.java)


        binding.setLifecycleOwner(this)
        binding.rvRepos.adapter = adapter
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.addItemDecoration(ItemDivider(context))
        binding.rvRepos.setEmptyView(binding.emptyState.emptyView)


        SwipeRefreshLayoutHelper().init(binding.swipeRefreshLayout)

        homeViewModel.repoList.observe(this, Observer {
            adapter.submitList(it)
        })

        homeViewModel.networkState.observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = it == NetworkState.LOADING
        })


        homeViewModel.blank.postValue("")

    }


}
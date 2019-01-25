package com.ven10.example.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ven10.example.R
import com.ven10.example.databinding.FragmentHomeBinding
import com.ven10.example.model.GitRepo
import com.ven10.example.ui.base.BaseFragment
import com.ven10.example.ui.detail.DetailActivity
import com.ven10.example.utils.NetworkState
import com.ven10.example.utils.Status
import com.ven10.example.views.CircleImageView
import com.ven10.example.views.ItemDivider
import com.ven10.example.views.SwipeRefreshLayoutHelper
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel

    private val listener = object : HomeItemListener {
        override fun onItemClick(item: GitRepo, img: View) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("repo", item)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, img, img.transitionName).toBundle()
                startActivity(intent, bundle)
                return
            }
            startActivity(intent)
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

            if (it.status == Status.FAILED) {
                showSnackBarError(it)
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.refresh()
        }

        homeViewModel.getTrendingRepos()

    }

    private fun showSnackBarError(it: NetworkState) {
        val snackbar = Snackbar.make(binding.root,
                it.msg.toString(), Snackbar.LENGTH_INDEFINITE)
        val snackBarLayout = snackbar.getView() as Snackbar.SnackbarLayout
        snackBarLayout.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        (snackBarLayout.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView)
                .setTextColor(context!!.resources.getColor(android.R.color.white))

        (snackBarLayout.findViewById<View>(com.google.android.material.R.id.snackbar_action) as TextView)
                .setTextColor(context!!.resources.getColor(android.R.color.white))

        snackbar.setAction(getString(R.string.retry)) {
            snackbar.dismiss()
            homeViewModel.refresh()
        }
        snackbar.show()
    }


}
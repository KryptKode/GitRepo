package com.ven10.example.ui.detail

import android.os.Bundle
import com.ven10.example.ui.base.BaseFragmentActivity
import javax.inject.Inject

class DetailActivity : BaseFragmentActivity<DetailFragment>() {

    @Inject lateinit var fragment: DetailFragment

    override fun accessFragment(): DetailFragment {
        val bundle = Bundle()
        bundle.putParcelable("repo", intent?.extras?.getParcelable("repo"))
        fragment.arguments = bundle
        return fragment
    }
}
package com.ven10.example.ui.home

import com.ven10.example.ui.base.BaseFragmentActivity
import javax.inject.Inject

class HomeActivity : BaseFragmentActivity<HomeFragment>() {
    @Inject lateinit var fragment: HomeFragment
    override fun accessFragment(): HomeFragment {
        return fragment
    }

}

package com.study.mybookshelf.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.study.mybookshelf.R
import com.study.mybookshelf.ui.TabPagerAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        tabLayout= root.findViewById<TabLayout>(R.id.tab_layout)
        viewPager = root.findViewById<ViewPager2>(R.id.pager)

        viewPager!!.setAdapter(TabPagerAdapter(childFragmentManager, lifecycle))
        TabLayoutMediator(tabLayout!!, viewPager!!, TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab1_library)
                1 -> tab.text = getString(R.string.tab2_borrowed_books)
                2 -> tab.text = getString(R.string.tab3_lended_books)
            }
        }).attach()
        return root
    }
}
package com.study.mybookshelf.ui.other

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.study.mybookshelf.ui.fragments.BorrowedBooksFragment
import com.study.mybookshelf.ui.fragments.LendedBooksFragment
import com.study.mybookshelf.ui.fragments.LibraryFragment

class TabPagerAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {
    private val int_items = 3
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = LibraryFragment()
            1 -> fragment = BorrowedBooksFragment()
            2 -> fragment = LendedBooksFragment()

        }
        return fragment!!
    }
    override fun getItemCount(): Int {
        return int_items
    }

}

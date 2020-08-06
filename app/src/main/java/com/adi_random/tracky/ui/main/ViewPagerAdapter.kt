package com.adi_random.tracky.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adi_random.tracky.ui.main.readingList.ReadingListFragment

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

//    TODO: Create a separate fragment for the DONE list
override fun createFragment(position: Int): Fragment {
    val fragment = ReadingListFragment()
    fragment.arguments = Bundle().apply {
        putInt(ReadingListFragment.EXTRA_TYPE, position)
    }
    return fragment
}
}
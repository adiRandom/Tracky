package com.adi_random.tracky.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.adi_random.tracky.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter;
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Init the view pager
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager2 = binding.viewPager
        viewPager2.adapter = viewPagerAdapter

        //init the tab layout

        binding.tabLayout.apply {
            TabLayoutMediator(this, viewPager2) { tab, position ->
                tab.text = TAB_LAYOUT_LABELS[position]
            }.attach()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
        private val TAB_LAYOUT_LABELS = arrayOf("TO BE READ", "READING", "DONE")
    }
}
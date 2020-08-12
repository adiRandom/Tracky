package com.adi_random.tracky.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.adi_random.tracky.R
import com.adi_random.tracky.databinding.FragmentMainBinding


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
        viewPager2.isUserInputEnabled = false

        //Link viewpager and bottom navigation

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_to_be_read_list -> {
                    viewPager2.setCurrentItem(0)
                    true
                }
                R.id.nav_reading_list -> {
                    viewPager2.setCurrentItem(1)
                    true
                }
                R.id.nav_done_list -> {
                    viewPager2.setCurrentItem(2)
                    true
                }
                else -> false
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavigation.menu.findItem(R.id.nav_to_be_read_list).isChecked =
                        true
                    1 -> binding.bottomNavigation.menu.findItem(R.id.nav_reading_list).isChecked =
                        true
                    2 -> binding.bottomNavigation.menu.findItem(R.id.nav_done_list).isChecked =
                        true
                }
            }

        })


    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
        private val TAB_LAYOUT_LABELS = arrayOf("TO BE READ", "READING", "DONE")
        private val TAB_LAYOUT_ICONS = arrayOf(
            R.drawable.ic_to_be_read_book,
            R.drawable.ic_reading_book_24,
            R.drawable.ic_baseline_done_24
        )
    }
}
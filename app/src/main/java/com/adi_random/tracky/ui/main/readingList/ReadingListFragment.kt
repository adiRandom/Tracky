package com.adi_random.tracky.ui.main.readingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.adi_random.tracky.databinding.ReadingListFragmentBinding

class ReadingListFragment : Fragment() {

    companion object {
        fun newInstance() =
            ReadingListFragment()

        public const val EXTRA_TYPE = "extraType"
    }

    private val viewModel: ReadingListViewModel by viewModels<ReadingListViewModel> {
        val type = ReadingListType.getType(arguments?.getInt(EXTRA_TYPE) ?: 3)
        ReadingListViewModelFactory(requireActivity().application, type)
    }
    private lateinit var binding: ReadingListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.readingList.observe(this) {
            val adapter = ReadingListAdapter(it)
            binding.readingListRecyclerView.swapAdapter(adapter, false)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReadingListFragmentBinding.inflate(inflater, container, false)

        //Init the recycler view

        val layoutManager = LinearLayoutManager(activity)
        val adapter = ReadingListAdapter(viewModel.readingList.value)
        val _value = viewModel.readingList.value
        binding.readingListRecyclerView.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
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

        const val EXTRA_TYPE = "extraType"
    }

    private val viewModel: ReadingListViewModel by viewModels<ReadingListViewModel> {
        val type = ReadingListType.getType(arguments?.getInt(EXTRA_TYPE) ?: 3)
        ReadingListViewModelFactory(requireActivity().application, type)
    }
    private lateinit var binding: ReadingListFragmentBinding
    private lateinit var readingListAdapter: ReadingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.readingList.observe(this) {
            this.readingListAdapter.changeData(viewModel)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReadingListFragmentBinding.inflate(inflater, container, false)

        //Init the recycler view

        val layoutManager = LinearLayoutManager(activity)
        this.readingListAdapter = ReadingListAdapter(viewModel)
        this.binding.readingListRecyclerView.apply {
            adapter = readingListAdapter
            this.layoutManager = layoutManager
        }

        return binding.root
    }

}
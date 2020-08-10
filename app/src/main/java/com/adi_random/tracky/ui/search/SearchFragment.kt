package com.adi_random.tracky.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.adi_random.tracky.databinding.SearchFragmentBinding
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.models.GoodreadsBookComparator
import com.adi_random.tracky.ui.main.search.SearchResultsListViewAdapter

val QUERY_STATE_CODE = "query"

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: SearchFragmentBinding
    private lateinit var searchResultListViewAdapter: SearchResultsListViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.query = requireArguments().getString(Intent.ACTION_SEARCH)!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        //Init the RecyclerView

        searchResultListViewAdapter =
            SearchResultsListViewAdapter(viewModel, requireContext(), GoodreadsBookComparator)
        val recyclerView = binding.searchResultsRecyclerView
        val listViewManager = LinearLayoutManager(activity)
        recyclerView.adapter = searchResultListViewAdapter
        recyclerView.layoutManager = listViewManager

        //Observe paging data

        val searchResultObserver = Observer<PagingData<GoodreadsBook>> {
            searchResultListViewAdapter.submitData(lifecycle, it)
        }
        viewModel.searchResults.observe(this.requireActivity(), searchResultObserver)


        return binding.root
    }


}
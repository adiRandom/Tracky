package com.adi_random.tracky.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adi_random.tracky.databinding.SearchFragmentBinding
import com.adi_random.tracky.models.GoodreadsBook

val QUERY_STATE_CODE = "query"

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: SearchFragmentBinding
    private lateinit var searchResultListViewAdapter: SearchResultsListViewAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val query = arguments?.getString(Intent.ACTION_SEARCH);

        //Create observers

        val searchResultObserver = Observer<Array<GoodreadsBook>> {
            searchResultListViewAdapter.setData(it)
        }
        viewModel.getSearchResults().observe(this, searchResultObserver)


        //Perform the search
        viewModel.search(query)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

//        Init the recycler view
        val listViewManager = LinearLayoutManager(activity)
        val initialData = MutableLiveData<Array<GoodreadsBook>>(viewModel.getSearchResults().value)
        searchResultListViewAdapter = SearchResultsListViewAdapter(initialData)

        binding.searchResultsRecyclerView.apply {
            layoutManager = listViewManager;
            adapter = searchResultListViewAdapter
        }

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


}
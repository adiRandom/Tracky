package com.adi_random.tracky.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.SearchResultBinding

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class SearchResultsListViewAdapter(
    private var viewModel: SearchViewModel,
    private val ctx: Context
) :
    RecyclerView.Adapter<SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val binding = SearchResultBinding.inflate(inflater, parent, false)
        return SearchResultViewHolder(binding);
    }

    override fun getItemCount(): Int {
        return viewModel.getSearchResults().value?.size ?: 0;
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.update(viewModel.getSearchResults().value?.get(position)) {
            viewModel.addToReadingList(ctx, viewModel.getSearchResults().value?.get(position))
        }
    }

}
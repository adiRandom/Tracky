package com.adi_random.tracky.ui.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.adi_random.tracky.databinding.SearchResultBinding
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.search.SearchResultViewHolder
import com.adi_random.tracky.ui.search.SearchViewModel

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class SearchResultsListViewAdapter(
    private val viewModel: SearchViewModel,
    private val ctx: Context,
    differCallback: DiffUtil.ItemCallback<GoodreadsBook>
) :
    PagingDataAdapter<GoodreadsBook, SearchResultViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultBinding.inflate(inflater, parent, false)
        return SearchResultViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.update(item) {
            viewModel.addToReadingList(ctx, item)
        }
    }

}

package com.adi_random.tracky.ui.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.adi_random.tracky.R
import com.adi_random.tracky.databinding.SearchResultBinding
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListType
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
//        The icon the add button will have based on the owner
        val buttonIcon = when (item?.owner) {
            ReadingListType.TO_BE_READ -> R.drawable.ic_to_be_read_book
            ReadingListType.READING -> R.drawable.ic_reading_book_24
            ReadingListType.DONE -> R.drawable.ic_baseline_done_24
            ReadingListType.UNSET -> R.drawable.ic_baseline_add_24
            null -> R.drawable.ic_baseline_add_24
        }
        holder.update(item, buttonIcon = buttonIcon) {
            viewModel.addToReadingList(ctx, item)
        }
    }

}

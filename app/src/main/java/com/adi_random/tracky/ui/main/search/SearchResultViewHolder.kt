package com.adi_random.tracky.ui.main.search

import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.SearchResultBinding
import com.adi_random.tracky.models.GoodreadsBook

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class SearchResultViewHolder(private val binding: SearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    public fun update(book: GoodreadsBook?) {
        binding.result = book;
        binding.executePendingBindings();
    }
}
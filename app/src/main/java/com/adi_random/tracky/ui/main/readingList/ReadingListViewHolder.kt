package com.adi_random.tracky.ui.main.readingList

import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.ReadingListItemBinding
import com.adi_random.tracky.models.GoodreadsBook

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
//TODO: Add actions based on ReadingListType
class ReadingListViewHolder(private var binding: ReadingListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(newData: GoodreadsBook?) {
        binding.book = newData;
        binding.executePendingBindings()
    }

}
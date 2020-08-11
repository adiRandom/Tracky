package com.adi_random.tracky.ui.main.readingList

import com.adi_random.tracky.databinding.DoneReadingListItemBinding
import com.adi_random.tracky.models.GoodreadsBook

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
//TODO: Add actions based on ReadingListType
class DoneReadingListViewHolder(
    private var binding: DoneReadingListItemBinding
) :
    AbstractReadingListViewHolder(binding.root) {


    override fun bind(newData: GoodreadsBook?) {
        binding.book = newData
        binding.executePendingBindings()
    }

}
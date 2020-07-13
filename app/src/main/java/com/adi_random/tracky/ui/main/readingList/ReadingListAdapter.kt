package com.adi_random.tracky.ui.main.readingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.ReadingListItemBinding
import com.adi_random.tracky.models.GoodreadsBook

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
class ReadingListAdapter(private val dataset: LiveData<Array<GoodreadsBook>>) :
    RecyclerView.Adapter<ReadingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReadingListItemBinding.inflate(inflater, parent, false);
        return ReadingListViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.value?.size ?: 0

    override fun onBindViewHolder(holder: ReadingListViewHolder, position: Int) {
        holder.bind(dataset.value?.get(position))
    }
}
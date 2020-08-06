package com.adi_random.tracky.ui.main.readingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.ReadingListItemBinding

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
class ReadingListAdapter(private var viewModel: ReadingListViewModel) :
    RecyclerView.Adapter<ReadingListViewHolder>() {

    private var dataset = viewModel.readingList.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReadingListItemBinding.inflate(inflater, parent, false)
        return ReadingListViewHolder(binding) {
            viewModel.moveToTheNextList(it)
        }
    }

    fun changeData(newData: ReadingListViewModel) {
        viewModel = newData
        this.dataset = newData.readingList.value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataset?.size ?: 0

    override fun onBindViewHolder(holder: ReadingListViewHolder, position: Int) {
        holder.bind(this.dataset?.get(position))
    }


}
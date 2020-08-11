package com.adi_random.tracky.ui.main.readingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.DoneReadingListItemBinding
import com.adi_random.tracky.databinding.ReadingListItemBinding

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */
class ReadingListAdapter(private var viewModel: ReadingListViewModel) :
    RecyclerView.Adapter<AbstractReadingListViewHolder>() {

    private var dataset = viewModel.readingList.value

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractReadingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (this.viewModel.readingListType === ReadingListType.DONE) {
            val binding = DoneReadingListItemBinding.inflate(inflater, parent, false)
            DoneReadingListViewHolder(binding)
        } else {
            val binding = ReadingListItemBinding.inflate(inflater, parent, false)
            ReadingListViewHolder(binding) {
                viewModel.moveToTheNextList(it)
            }
        }
    }

    fun changeData(newData: ReadingListViewModel) {
        viewModel = newData
        this.dataset = newData.readingList.value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataset?.size ?: 0

    override fun onBindViewHolder(holder: AbstractReadingListViewHolder, position: Int) {
        holder.bind(this.dataset?.get(position))
    }

}
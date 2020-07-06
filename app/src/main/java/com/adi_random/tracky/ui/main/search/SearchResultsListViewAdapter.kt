package com.adi_random.tracky.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.SearchResultBinding
import com.adi_random.tracky.models.GoodreadsBook

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class SearchResultsListViewAdapter(private var dataset: MutableLiveData<Array<GoodreadsBook>>) :
    RecyclerView.Adapter<SearchResultViewHolder>() {
    //    TODO: Override the setData method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val view = SearchResultBinding.inflate(inflater, parent, false)
        return SearchResultViewHolder(view);
    }

    override fun getItemCount(): Int {
        val data = dataset.value
        return dataset.value?.size ?: 0;
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.update(dataset.value?.get(position))
    }

    fun setData(newData: Array<GoodreadsBook>) {
        dataset.postValue(newData)
        notifyDataSetChanged()
    }

}
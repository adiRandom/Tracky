package com.adi_random.tracky.ui.main.readingList

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.ReadingListItemBinding
import com.adi_random.tracky.models.GoodreadsBook
import com.squareup.picasso.Picasso

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

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: View, imageUrl: String) {
            Picasso.with(view.context).load(imageUrl).into(view as ImageView)
        }
    }
}
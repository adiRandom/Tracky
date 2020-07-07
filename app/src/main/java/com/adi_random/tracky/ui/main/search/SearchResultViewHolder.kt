package com.adi_random.tracky.ui.main.search

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adi_random.tracky.databinding.SearchResultBinding
import com.adi_random.tracky.models.GoodreadsBook
import com.squareup.picasso.Picasso

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class SearchResultViewHolder(private val binding: SearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    public fun update(book: GoodreadsBook?) {
        binding.result = book;
        binding.executePendingBindings();
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: View, imageUrl: String) {
            Picasso.with(view.context).load(imageUrl).into(view as ImageView)
        }
    }

}
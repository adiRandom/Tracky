package com.adi_random.tracky.ui.search

import android.graphics.drawable.Drawable
import android.util.Log
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
    public fun update(book: GoodreadsBook?, onAdd: () -> Unit) {
        binding.model = SearchResultViewHolderViewModel(book, onAdd);
        binding.executePendingBindings();
    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl", "error"], requireAll = true)
        fun setImageUrl(view: View, imageUrl: String?, error: Drawable?) {
            Log.d("Error-image", error.toString())
            Picasso.with(view.context).load(imageUrl).error(error).into(view as ImageView)
        }
    }

}

class SearchResultViewHolderViewModel(val book: GoodreadsBook?, val _onAdd: () -> Unit) {
    fun onAdd() {
        _onAdd()
    }
}
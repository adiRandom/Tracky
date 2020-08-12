package com.adi_random.tracky.ui.main.readingList

import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.adi_random.tracky.R
import com.adi_random.tracky.databinding.ReadingListItemBinding
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.views.FreezableRecyclerView

/**
 * Created by Adrian Pascu on 13-Jul-20.
 */

abstract class AbstractReadingListViewHolder(root: View) :
    ViewHolder(root) {
    abstract fun bind(newData: GoodreadsBook?)
}


//TODO: Add actions based on ReadingListType
class ReadingListViewHolder(
    private var binding: ReadingListItemBinding,
    private val parent: FreezableRecyclerView,
    private val moveBookToNextList: (pos: Int) -> Unit
) :
    AbstractReadingListViewHolder(binding.root) {

    private var animationEndId: Int = 0

    init {
//        Add the move and dismiss animation

        binding.readingListItemMotion.setTransitionListener(object :
            MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }


            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
//                Set the end ID
                animationEndId = p2
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                //Add border
                if (p3 > 0) {
                    binding.readingListItemLayout.setBackgroundResource(R.drawable.item_border)
                }

                //Freeze the recyclerview

                this@ReadingListViewHolder.parent.swipeFrozen = p3 != 1f
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
//                Check if it's end and not start

                if (p1 == animationEndId) {
                    moveBookToNextList(bindingAdapterPosition)

                } else {
//                    Remove the border
                    binding.readingListItemLayout.setBackgroundResource(R.drawable.item_background)
                }

            }
        })
    }

    override fun bind(newData: GoodreadsBook?) {
        binding.book = newData
        if (binding.book?.owner == ReadingListType.READING)
            binding.imageButton.setImageResource(R.drawable.ic_baseline_done_24)

        //Reset the transition
        binding.readingListItemMotion.apply {
            progress = 0f
            setTransition(R.id.start, R.id.end)
        }
        binding.readingListItemLayout.setBackgroundResource(R.drawable.item_background)

        binding.executePendingBindings()
    }

}
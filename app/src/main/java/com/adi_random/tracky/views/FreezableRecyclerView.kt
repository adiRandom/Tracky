package com.adi_random.tracky.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Adrian Pascu on 12.08.2020.
 */

class FreezableRecyclerView : RecyclerView {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    var swipeFrozen: Boolean = false

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return if (swipeFrozen)
            false
        else
            super.onInterceptTouchEvent(e)
    }


}
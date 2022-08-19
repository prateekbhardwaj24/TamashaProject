package com.example.androidcomponents.views

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class RvItemDecoration() : RecyclerView.ItemDecoration() {

    val HORIZONTAL = 0
    val VERTICAL = 1
    val GRID = 2
    private var spacing by Delegates.notNull<Int>()
    private var displayMode by Delegates.notNull<Int>()
    private var showStart by Delegates.notNull<Boolean>()
    private var showEnd by Delegates.notNull<Boolean>()
    private var startSpacing by Delegates.notNull<Int>()
    private var endSpacing by Delegates.notNull<Int>()

    private var spanCount = 0
    private var includeEdge = false

    constructor(
        spacing: Int,
        displayMode: Int,
        showStart: Boolean,
        showEnd: Boolean,
        startSpacing: Int,
        endSpacing: Int
    ) : this() {
        this.spacing = spacing
        this.displayMode = displayMode
        this.showEnd = showEnd
        this.startSpacing = startSpacing
        this.showStart = showStart
        this.endSpacing = endSpacing
    }
    constructor(
         spanCount:Int,  spacing:Int,  includeEdge:Boolean

    ) : this() {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

//    private fun setSpacingForDirection(
//        outRect: Rect,
//        layoutManager: RecyclerView.LayoutManager,
//        position: Int,
//        itemCount: Int,
//        parent: RecyclerView
//    ) {
//
//
//        // Resolve display mode automatically
//        if (displayMode == -1) {
//            displayMode = resolveDisplayMode(layoutManager)
//        }
//        when (displayMode) {
//            HORIZONTAL -> {
//                outRect.left =
//                    if (showStart && position == 0) startSpacing else if (position > 0) spacing else 0
//                outRect.right =
//                    if (showEnd && position == itemCount - 1) endSpacing else if (position == itemCount - 1) spacing else 0
//                outRect.top = 0
//                outRect.bottom = 0
//            }
//            VERTICAL -> {
//                outRect.left = 0
//                outRect.right = 0
//                outRect.top =
//                    if (showStart && position == 0) startSpacing else if (position > 0) spacing else 0
//                outRect.bottom =
//                    if (showEnd && position == itemCount - 1) endSpacing else if (position == itemCount - 1) spacing else 0
//            }
//            GRID -> if (layoutManager is GridLayoutManager) {
//                val cols = layoutManager.spanCount
//                val rows = itemCount / cols + if (itemCount % cols > 0) 1 else 0
//
//
//                outRect.left = spacing
//                outRect.right = if (position % cols == cols - 1) spacing else 0
//                outRect.top = spacing
//                outRect.bottom = if (position / cols == rows - 1) spacing else 0
//            }
//        }
//    }


    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager.canScrollHorizontally()) HORIZONTAL else VERTICAL
    }



}
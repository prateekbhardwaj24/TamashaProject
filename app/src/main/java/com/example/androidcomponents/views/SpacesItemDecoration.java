package com.example.androidcomponents.views;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;
    private int displayMode;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int GRID = 2;
    private int startSpacing = 0;
    private int endSpacing = 0;
    private boolean showStart = true;
    private boolean showEnd = true;

    private boolean includeEdge = false;

    public SpacesItemDecoration(int spacing, int displayMode, boolean showStart, boolean
            showEnd) {
        this.spacing = spacing;
        this.startSpacing = spacing;
        this.endSpacing = spacing;
        this.displayMode = displayMode;
        this.showStart = showStart;
        this.showEnd = showEnd;
    }


    public SpacesItemDecoration(int spacing,int displayMode, boolean includeEdge) {
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.displayMode = displayMode;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        setSpacingForDirection(outRect, layoutManager, position, itemCount, parent);
    }

    private void setSpacingForDirection(Rect outRect, RecyclerView.LayoutManager layoutManager, int position, int itemCount, RecyclerView parent) {
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager);
        }

        switch (displayMode){
            case HORIZONTAL:
                outRect.left = (showStart && position == 0) ? startSpacing : ((position > 0) ? spacing : 0);
                outRect.right = (showEnd && position == itemCount - 1) ? endSpacing : ((position == itemCount - 1) ? spacing : 0);
                outRect.top = spacing;
                outRect.bottom = spacing;
                break;
            case VERTICAL:
                outRect.left = spacing;
                outRect.right = spacing;
                outRect.top = (showStart && position == 0) ? startSpacing : ((position > 0) ? spacing : 0);
                outRect.bottom = (showEnd && position == itemCount - 1) ? endSpacing : ((position == itemCount - 1) ? spacing : 0);
                break;
            case GRID:
                if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    int cols = gridLayoutManager.getSpanCount();
                    int rows = (itemCount / cols) + (itemCount % cols > 0 ? 1 : 0);

                    outRect.left = spacing/2;
                    outRect.right = spacing/2;
                    outRect.bottom = spacing/2;
                    outRect.top = spacing/2;
                    Log.d("colsrow","row->"+outRect.bottom+" cols->"+position);

//                    outRect.left = spacing - cols * spacing / itemCount; // spacing - column * ((1f / spanCount) * spacing)
//                    outRect.right = spacing - cols * spacing / itemCount; // (column + 1) * ((1f / spanCount) * spacing)
//
//                    if (position < itemCount) { // top edge
//                        outRect.top = spacing;
//                    }
//                    outRect.bottom = spacing;
                }
                break;
        }

    }

    private int resolveDisplayMode(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) return GRID;
        if (layoutManager instanceof LinearLayoutManager && layoutManager.canScrollHorizontally()) return HORIZONTAL;
        return VERTICAL;
    }
}
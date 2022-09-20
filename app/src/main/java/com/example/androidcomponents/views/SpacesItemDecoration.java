package com.example.androidcomponents.views;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;
    private int displayMode;
    public static final int VERTICAL = 1;
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

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        setSpacingForDirection(outRect, layoutManager, position, itemCount, parent);
    }

    private void setSpacingForDirection(Rect outRect, RecyclerView.LayoutManager layoutManager, int position, int itemCount, RecyclerView parent) {
        if (displayMode == VERTICAL) {
            outRect.left = spacing;
            outRect.right = spacing;
            outRect.top = (showStart && position == 0) ? startSpacing : ((position > 0) ? spacing : 0);
            outRect.bottom = (showEnd && position == itemCount - 1) ? endSpacing : 0;
            Log.d("decorea", "left:" + outRect.left + " right:" + outRect.right + " top:" + outRect.top + " bottom:" + outRect.bottom);
        }

    }


}
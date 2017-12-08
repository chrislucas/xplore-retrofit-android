package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by r028367 on 08/12/2017.
 */

public class SimpleRecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private Context mContext;

    public SimpleRecyclerViewItemDecoration(Drawable mDivider, Context mContext) {
        this.mDivider = mDivider;
        this.mContext = mContext;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}

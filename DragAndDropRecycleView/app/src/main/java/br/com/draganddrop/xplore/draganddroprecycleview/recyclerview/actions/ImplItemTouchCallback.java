package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.viewholder.AbstractViewHolder;

/**
 * Created by r028367 on 08/12/2017.
 */

public class ImplItemTouchCallback extends ItemTouchHelper.Callback {

    ItemTouchHelperAdapter mItemTouchHelperAdapter;

    public ImplItemTouchCallback(ItemTouchHelperAdapter mItemTouchHelperAdapter) {
        this.mItemTouchHelperAdapter = mItemTouchHelperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView
            , RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mItemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition()
                , target.getAdapterPosition());
        Log.i("ON_MOVE", "ELEMENTO DA LISTA SENDO MANIPULADO");
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mItemTouchHelperAdapter.onItemSwiped(viewHolder.getAdapterPosition());
        Log.i("ON_SWIPED"
                , String.format("ELEMENTO DA LISTA SENDO DESLIZADO PARA %d", direction));
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView
            , RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.i("ON_SWIPED_CHILD_DRAW", String.format("REDESENHANDO A LISTA em (%f, %f)", dX, dY));
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if(viewHolder instanceof ItemTouchHelperViewHolder) {
                AbstractViewHolder viewHolderListPersona = (AbstractViewHolder) viewHolder;
                viewHolderListPersona.getItemView().setTranslationX(dX);
            }
        }
        else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    private void logActionStateOnSelectedChanged(int actionState) {
        String tag = "SELECTED_CHANGED";
        switch (actionState) {
            case ItemTouchHelper.ACTION_STATE_SWIPE:
                Log.i(tag, "ACTION_STATE_SWIPE");
                break;
            case ItemTouchHelper.ACTION_STATE_DRAG:
                Log.i(tag, "ACTION_STATE_DRAG");
                break;
            case ItemTouchHelper.ACTION_STATE_IDLE:
                Log.i(tag, "ACTION_STATE_IDLE");
                break;
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        /**
         * ItemTouchHelper is in idle state. At this state, either there is no related motion event by
         * the user or latest motion events have not yet triggered a swipe or drag.
         *
         * IDLE - Ocioso
         */
        logActionStateOnSelectedChanged(actionState);
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if(viewHolder instanceof ItemTouchHelperViewHolder) {
                ItemTouchHelperViewHolder touchHelperViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                touchHelperViewHolder.selectedState();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if(viewHolder instanceof ItemTouchHelperViewHolder) {
            ItemTouchHelperViewHolder touchHelperViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            touchHelperViewHolder.clearState();
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


}

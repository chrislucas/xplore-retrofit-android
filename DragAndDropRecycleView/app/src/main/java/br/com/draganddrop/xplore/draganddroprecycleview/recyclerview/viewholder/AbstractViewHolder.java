package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by r028367 on 08/12/2017.
 */

public class AbstractViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    public AbstractViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }
    public View getItemView() {
        return itemView;
    }
}

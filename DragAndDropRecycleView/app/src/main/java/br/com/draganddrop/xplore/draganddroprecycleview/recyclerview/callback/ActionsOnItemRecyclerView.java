package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback;

import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.OnListChangeListener;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.OnStartDragListener;

/**
 * Created by r028367 on 08/12/2017.
 */

public interface ActionsOnItemRecyclerView<T> extends AdapterOnItemClick<T>
        , OnListChangeListener<T>, OnStartDragListener {
}

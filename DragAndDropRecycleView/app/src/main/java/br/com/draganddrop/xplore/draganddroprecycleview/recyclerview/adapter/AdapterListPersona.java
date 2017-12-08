package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

import br.com.draganddrop.xplore.draganddroprecycleview.Persona;
import br.com.draganddrop.xplore.draganddroprecycleview.R;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.ItemTouchHelperAdapter;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.ActionsOnItemRecyclerView;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.viewholder.ViewHolderListPersona;


/**
 * Created by r028367 on 07/12/2017.
 */

public class AdapterListPersona extends RecyclerView.Adapter<ViewHolderListPersona> implements ItemTouchHelperAdapter {
    private List<Persona> personas;
    private ActionsOnItemRecyclerView<Persona> actionsOnItemRecyclerView;
    private Context context;
    public AdapterListPersona(List<Persona> personas, ActionsOnItemRecyclerView<Persona> actionsOnItemRecyclerView) {
        this.personas = personas;
        this.actionsOnItemRecyclerView = actionsOnItemRecyclerView;
    }

    @Override
    public ViewHolderListPersona onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_adapter_item_list_persona, parent, false);
        this.context = parent.getContext();
        return new ViewHolderListPersona(rootLayout);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final ViewHolderListPersona holder, int position) {
        Persona persona = personas.get(position);
        holder.getName().setText(persona.getName());
        holder.getEmail().setText(persona.getEmail());
        holder.getPhone().setText(persona.getPhone());
        holder.bind(actionsOnItemRecyclerView, persona);
        ImageView imageView = holder.getImageView();
        imageView.performClick();
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        actionsOnItemRecyclerView.onStartDrag(holder);
                        break;
                }
                return false;
            }
        };
        imageView.setOnTouchListener(onTouchListener);
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    /**
     * {@link ItemTouchHelperAdapter}
     * */
    @Override
    public void onItemMove(int newPosition, int lastPosition) {
        Log.i("SWAP", String.format("%d,%d\n", newPosition, lastPosition));
        Log.i("SWAP", String.format("%s\n%s", personas.get(newPosition).toString()
                , personas.get(lastPosition).toString()));
        Collections.swap(personas, newPosition, lastPosition);
        notifyItemMoved(newPosition, lastPosition);
        actionsOnItemRecyclerView.onListChange(personas);
    }

    /**
     * */
    @Override
    public void onItemSwiped(int position) {
        Log.i("ON_ITEM_DISMISSED", personas.get(position).toString());
        personas.remove(position);
        notifyItemRemoved(position);
        actionsOnItemRecyclerView.onListChange(personas);
    }
}

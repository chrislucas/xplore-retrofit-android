package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.draganddrop.xplore.draganddroprecycleview.Persona;
import br.com.draganddrop.xplore.draganddroprecycleview.R;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.AdapterOnItemClick;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.viewholder.ViewHolderListPersona;


/**
 * Created by r028367 on 07/12/2017.
 */

public class AdapterListPersona extends RecyclerView.Adapter<ViewHolderListPersona> {

    private List<Persona> personas;
    private AdapterOnItemClick<Persona> adapterOnItemClick;
    private Context context;

    public AdapterListPersona(List<Persona> personas, AdapterOnItemClick<Persona> adapterOnItemClick) {
        this.personas = personas;
        this.adapterOnItemClick = adapterOnItemClick;
    }

    @Override
    public ViewHolderListPersona onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_adapter_item_list_persona, parent, true);
        this.context = parent.getContext();
        return new ViewHolderListPersona(rootLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolderListPersona holder, int position) {
        Persona persona = personas.get(position);
        holder.getName().setText(persona.getName());
        holder.getEmail().setText(persona.getEmail());
        holder.getPhone().setText(persona.getPhone());
        holder.bind(adapterOnItemClick, persona);
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }
}

package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.draganddrop.xplore.draganddroprecycleview.Persona;
import br.com.draganddrop.xplore.draganddroprecycleview.R;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.AdapterOnItemClick;

/**
 * Created by r028367 on 06/12/2017.
 */

public class ViewHolderListPersona extends RecyclerView.ViewHolder {

    private TextView name, email, phone;
    private View itemView;
    public ViewHolderListPersona(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.name    = itemView.findViewById(R.id.persona_name);
        this.email   = itemView.findViewById(R.id.persona_email);
        this.phone   = itemView.findViewById(R.id.persona_phone);
    }

    public TextView getName() {
        return name;
    }

    public TextView getEmail() {
        return email;
    }

    public TextView getPhone() {
        return phone;
    }

    public void bind(final AdapterOnItemClick<Persona> adapterOnItemClick, final Persona persona) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterOnItemClick.onClick(persona);
            }
        });
    }
}

package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.draganddrop.xplore.draganddroprecycleview.Persona;
import br.com.draganddrop.xplore.draganddroprecycleview.R;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.ItemTouchHelperViewHolder;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.ActionsOnItemRecyclerView;

/**
 * Created by r028367 on 06/12/2017.
 */

public class ViewHolderListPersona extends AbstractViewHolder implements ItemTouchHelperViewHolder {
    private TextView name, email, phone;

    private ImageView imageView;
    public ViewHolderListPersona(View itemView) {
        super(itemView);
        this.name        = itemView.findViewById(R.id.persona_name);
        this.email       = itemView.findViewById(R.id.persona_email);
        this.phone       = itemView.findViewById(R.id.persona_phone);
        this.imageView   = itemView.findViewById(R.id.image_persona);
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

    public ImageView getImageView() {
        return imageView;
    }

    public void bind(final ActionsOnItemRecyclerView<Persona> adapterOnItemClick, final Persona persona) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterOnItemClick.onClick(persona);
            }
        });
    }

    private String to() {
        return String.format("Persona: Nome: %s email: %s fone: %s", name.getText(), email.getText(), phone.getText());
    }

    @Override
    public void selectedState() {
        Log.i("ITEM_SELECTED", to());
    }

    @Override
    public void clearState() {
        Log.i("ITEM_CLEAR_SELECTED", to());
    }
}

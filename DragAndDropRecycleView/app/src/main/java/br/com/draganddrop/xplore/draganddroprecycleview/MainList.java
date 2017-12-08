package br.com.draganddrop.xplore.draganddroprecycleview;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.ImplItemTouchCallback;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.adapter.AdapterListPersona;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.ActionsOnItemRecyclerView;

public class MainList extends AppCompatActivity implements ActionsOnItemRecyclerView<Persona> {
    private RecyclerView recyclerView;
    private ItemTouchHelper itemTouchHelper;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public String brazilianPhoneMask(String phone) {
        String onlyNumbers = "\\d+$";
        String onlyNumbersPhone = "\\d{10,11}$";

        if( ! phone.matches(onlyNumbers) ) {
            throw new IllegalArgumentException("Envie apenas digitos");
        }

        if( ! phone.matches(onlyNumbersPhone) ) {
            throw new IllegalArgumentException("Envia de 8 a 9 digitos");
        }
        int f = 4 , s = 4;
        if( ( phone.length() == 11 ) )
            f = 5;
        String regex = String.format(Locale.getDefault(), "(\\d{2})(\\d{%d})(\\d{%d})", f, s);
        return phone.replaceAll(regex, "($1) $2-$3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        recyclerView = findViewById(R.id.list_personas);
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Artur Damaceno", brazilianPhoneMask("11222224567"), "arturdamaceno@icotel.com"));
        personas.add(new Persona("Bruno Almeida", brazilianPhoneMask("2233347568"), "brunoalmeida@icotel.com"));
        personas.add(new Persona("Renata Brandão", brazilianPhoneMask("21234567755"), "brunoalmeida@icotel.com"));
        personas.add(new Persona("Marta Azevedo", brazilianPhoneMask("21234567755"), "martaazevedo@icotel.com"));
        personas.add(new Persona("Bruno Vasconcelos", brazilianPhoneMask("21234567755"), "brunovasconcelos@icotel.com"));
        personas.add(new Persona("Alexandre Buarque", brazilianPhoneMask("21234567755"), "alexandrebuarque@icotel.com"));
        personas.add(new Persona("Franscisco Almeida Pires", brazilianPhoneMask("21234567755"), "fransciscoalmeida@icotel.com"));
        personas.add(new Persona("Daniela Almeida Pires", brazilianPhoneMask("21234567755"), "danielaalmeida@icotel.com"));
        personas.add(new Persona("Dainana Almeida Pires", brazilianPhoneMask("21234567755"), "daianaalmeida@icotel.com"));
        personas.add(new Persona("Gertrudes Gonçalves Albuquerque", brazilianPhoneMask("21234567755"), "gertrudesgoncalves@icotel.com"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterListPersona adapterListPersona = new AdapterListPersona(personas, this);
        ItemTouchHelper.Callback callbackItemTouch = new ImplItemTouchCallback(adapterListPersona);
        itemTouchHelper = new ItemTouchHelper(callbackItemTouch);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapterListPersona);
    }

    /**
     * {@link br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.AdapterOnItemClick}
     * */
    @Override
    public void onClick(Persona persona) {
        Snackbar.make(findViewById(R.id.wrapper_layout_list_persona)
                , persona.toString(), Snackbar.LENGTH_LONG).show();
    }

    /**
     * {@link br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.OnListChangeListener}
     * */
    @Override
    public void onListChange(List<Persona> list) {
        Log.i("ON_LIST_CHANGE"
                , String.format("Modificando uma lista de %s elementos"
                        , list.size()));
    }


    /**
     * {@link br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions.OnStartDragListener}
     * */
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Log.i("ON_START_DRAG", "Arrastando a viewHolder");
        itemTouchHelper.startDrag(viewHolder);
    }
}

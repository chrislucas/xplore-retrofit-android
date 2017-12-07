package br.com.draganddrop.xplore.draganddroprecycleview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.adapter.AdapterListPersona;
import br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.callback.AdapterOnItemClick;

public class MainList extends AppCompatActivity implements AdapterOnItemClick<Persona> {


    private RecyclerView recyclerView;

    public String brazillianPhoneMask(String phone) {
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
        String replace = String.format(Locale.getDefault(), "(\\d{2})(\\d{%d})(\\d{%d})", f, s);
        return phone.replace(replace, "($1) $2-$3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        recyclerView = findViewById(R.id.list_personas);
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Artur Damaceno", brazillianPhoneMask("11222224567"), "arturdamaceno@icotel.com"));
        personas.add(new Persona("Bruno Almeida", brazillianPhoneMask("2233347568"), "brunoalmeida@icotel.com"));
        personas.add(new Persona("Renata Brandão", brazillianPhoneMask("21234567755"), "brunoalmeida@icotel.com"));
        personas.add(new Persona("Marta Azevedo", brazillianPhoneMask("21234567755"), "martaazevedo@icotel.com"));
        personas.add(new Persona("Bruno Vasconcelos", brazillianPhoneMask("21234567755"), "brunovasconcelos@icotel.com"));
        personas.add(new Persona("Alexandre Buarque", brazillianPhoneMask("21234567755"), "alexandrebuarque@icotel.com"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterListPersona adapterListPersona = new AdapterListPersona(personas, this);
        recyclerView.setAdapter(adapterListPersona);
    }

    @Override
    public void onClick(Persona persona) {
        Snackbar.make(findViewById(R.id.wrapper_layout_list_persona)
                , persona.toString(), Snackbar.LENGTH_LONG).show();
    }
}

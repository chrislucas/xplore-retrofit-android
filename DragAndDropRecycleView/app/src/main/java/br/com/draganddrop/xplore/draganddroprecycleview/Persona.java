package br.com.draganddrop.xplore.draganddroprecycleview;

import java.util.Locale;

/**
 * Created by r028367 on 06/12/2017.
 */

public class Persona {

    private String name, phone, email;

    public Persona(String name, String phone, String email) {
        this.name  = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Nome: %s\nEmail: %s\nFone: %s", name, email, phone);
    }
}

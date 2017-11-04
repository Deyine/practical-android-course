package com.edacy.echat.contacts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by djiddou on 10/28/17.
 */

public final class Contact {

    // Bind Json field name with Java field name
    @SerializedName("name")
    String fullName;

    String phone;

    String picture;

    public Contact(String fullName, String phone, String picture) {
        this.fullName = fullName;
        this.phone = phone;
        this.picture = picture;
    }
}

package com.edacy.echat.service;

import com.edacy.echat.model.contacts.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by djiddou on 11/4/17.
 */

public interface ContactService {

    @GET("/contacts")
    public Call<List<Contact>> listContacts();
}

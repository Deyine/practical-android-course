package com.edacy.echat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.edacy.echat.contacts.Contact;
import com.edacy.echat.contacts.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.contact_list)
    RecyclerView contactList;

    // linearlayout for list
    private LinearLayoutManager manager;
    // Contact Adapter
    private ContactsAdapter adapter;

    private List<Contact> mContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ecran et le layout Link
        setContentView(R.layout.activity_contacts);
        // Initialiser ButterKnife
        ButterKnife.bind(this);

        // gérer l historique des versions
        setSupportActionBar(toolbar);

        // layout du recyclerView
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contactList.setLayoutManager(manager);

        // initialization des données
        mContacts.add(new Contact("Ndiate SENE", "77 777 77 77", "http://espacevelos.ch/wp-content/uploads/test.png"));
        mContacts.add(new Contact("Mactar TOURE", "77 777 78 77", "https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAUxAAAAJGM1ZmQ4OGE4LWNlNWEtNDBmZi05NTE2LTIwZWI1NTg2NGZmMQ.jpg"));

        // Adapter pour les données du recycler
        adapter = new ContactsAdapter(this, mContacts);
        contactList.setAdapter(adapter);
    }

}

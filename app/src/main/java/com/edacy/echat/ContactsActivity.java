package com.edacy.echat;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.edacy.echat.contacts.Contact;
import com.edacy.echat.contacts.ContactsAdapter;
import com.edacy.echat.service.ContactService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    Retrofit retrofit;

    ContactService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ecran et le layout Link
        setContentView(R.layout.activity_contacts);
        // Initialiser ButterKnife
        ButterKnife.bind(this);

        // gérer l historique des versions
        setSupportActionBar(toolbar);

        // Init services
        retrofit = new Retrofit.Builder()
                .baseUrl("http://demo1510063.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ContactService.class);

        // layout du recyclerView
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contactList.setLayoutManager(manager);

        initListContact();
    }


    /**
     * Init list of contacts
     */
    protected void initListContact(){
        service.listContacts().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if(response.isSuccessful()){
                    // Adapter pour les données du recycler
                    mContacts = response.body();
                    adapter = new ContactsAdapter(ContactsActivity.this, mContacts);
                    contactList.setAdapter(adapter);
                }else{
                    Toast.makeText(ContactsActivity.this, "Une erreur s'est produite", Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(ContactsActivity.this, "Une erreur s'est produite", Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

}

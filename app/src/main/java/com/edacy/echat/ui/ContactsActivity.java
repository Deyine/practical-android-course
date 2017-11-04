package com.edacy.echat.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.edacy.echat.Config;
import com.edacy.echat.EdacyApplication;
import com.edacy.echat.R;
import com.edacy.echat.model.contacts.Contact;
import com.edacy.echat.model.contacts.ContactDAO;
import com.edacy.echat.service.ContactService;
import com.edacy.echat.utils.UiUtils;

import java.net.URL;
import java.time.Duration;
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
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

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
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ContactService.class);

        initSwipeRefresh();

        // layout du recyclerView
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contactList.setLayoutManager(manager);

        initListContact();
    }

    /**
     * Init the swipe to refresh
     */
    private void initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if(UiUtils.isConnectingToInternet(ContactsActivity.this)) {
                            populateDBFromApi();
                        }else{
                            Snackbar.make(rootLayout, "No internet connection available", Snackbar.LENGTH_LONG).show();
                            swipeRefresh.setRefreshing(false);
                        }
                    }
                }
        );
    }

    /**
     * Call Api
     */
    private void populateDBFromApi(){
        service.listContacts().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if(response.isSuccessful()){
                    // Adapter pour les données du recycler
                    new InsertContactsTask().execute(response.body());
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

    /**
     * Init list of contacts
     */
    protected void initListContact(){
        new LoadContactsTask().execute();
    }

    /**
     * Set Data in the UI
     * @param contacts
     */
    private void setData(List<Contact> contacts){
        mContacts = contacts;
        adapter = new ContactsAdapter(ContactsActivity.this, mContacts);
        contactList.setAdapter(adapter);
        swipeRefresh.setRefreshing(false);
    }

    /**
     * Populate DB from API
     */
    class InsertContactsTask extends AsyncTask<List<Contact>, Void, Void> {

        @Override
        protected Void doInBackground(List<Contact>... lists) {
            EdacyApplication.getInstance().getDb().contactDao().insertAll(lists[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            initListContact();
        }
    }

    /**
     * Query Database to load contacts
     */
    class LoadContactsTask extends AsyncTask<Void, Void, List<Contact>> {


        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return EdacyApplication.getInstance().getDb().contactDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            setData(contacts);
        }
    }

}

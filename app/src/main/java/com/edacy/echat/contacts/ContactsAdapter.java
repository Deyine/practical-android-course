package com.edacy.echat.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edacy.echat.R;

import java.net.URI;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by djiddou on 10/28/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    List<Contact> mContacts;
    Context mContext;

    /**
     * Constructor.
     * @param contacts
     */
    public ContactsAdapter(Context context, List<Contact> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    /**
     * Create new views (invoked by the layout manager)
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        // Traduire et afficher un layout xml en une classe View
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        // instancier un viewHolder à partir de la View
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact c = mContacts.get(position);
        holder.fullName.setText(c.fullName);

        Glide.with(mContext)
                .load(c.picture)
                .into(holder.picture);
    }


    /**
     * Return the size of your dataset (invoked by the layout manager)
     * @return
     */
    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    /**
     *  View holder for adapter perf.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.full_name)
        TextView fullName;
        @BindView(R.id.picture)
        ImageView picture;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
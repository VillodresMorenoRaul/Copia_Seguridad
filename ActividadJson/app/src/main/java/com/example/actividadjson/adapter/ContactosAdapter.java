package com.example.actividadjson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadjson.R;
import com.example.actividadjson.model.Contacto;

import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {
    // Store a member variable for the contacts
    private List<Contacto> mContactos;
    // Pass in the contact array into the constructor
    public ContactosAdapter(List<Contacto> contactos) {
        mContactos = contactos;
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView mobileTextView;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            mobileTextView = (TextView) itemView.findViewById(R.id.mobile_phone);
        }
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactosAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Contacto contact = mContactos.get(position);
        // Set item views based on your views and data model
        TextView textView1 = holder.nameTextView;
        textView1.setText(contact.getNombre());
        TextView textView2 = holder.mobileTextView;
        textView2.setText(contact.getTelefono().getMovil());
    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContactos.size();
    }
    public void actualizar(List<Contacto> data) {
        this.mContactos = data;
        notifyDataSetChanged();
    }
}
package com.example.practicaretrofit.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaretrofit.Model.Repo;
import com.example.practicaretrofit.databinding.ItemViewBinding;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.MyViewHolder> {
    public ArrayList<Repo> mRepos;
    public  ReposAdapter(){
        this.mRepos = new ArrayList<>();
    }
    public void setRepos(ArrayList<Repo> repos) {
        mRepos = repos;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemViewBinding binding;//Name of the item_view.xml in camel case + "Binding"
        public MyViewHolder(ItemViewBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
    @NonNull
    @Override
    public ReposAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.MyViewHolder holder, int position) {
        // Get the data model based on position
        Repo repo = mRepos.get(position);
        holder.binding.textView1.setText(repo.getName());
        holder.binding.textView2.setText(repo.getDescription());
        // holder.binding.textView3.setText(repo.getCreatedAt());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                .withZone(ZoneId.systemDefault());
        // API 26 mínima para usar parse
        Instant instant = Instant.parse(repo.getCreatedAt());
        String output = formatter.format( instant );
        holder.binding.textView3.setText(output);
    }
    @Override
    public int getItemCount() {
        return mRepos.size();
    }
}

package com.example.practicaretrofit.Adapter;

import android.view.View;

import com.example.practicaretrofit.Model.Repo;
import com.example.practicaretrofit.Network.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;

public interface ClickListener {
    public void onClick(View view) {
        if (view == binding.fab) {
            String username = binding.editText.getText().toString();
            showMessage(username);
            if (username.isEmpty())
                showMessage("Debe dar un nombre");
            else {
                // poner cuadro de progreso, si se desea
                //Call<ArrayList<Repo>> call = apiService.listRepos(username);
                Call<ArrayList<Repo>> call = ApiAdapter.getInstance().listRepos(username);
                // uso con servidor web local
                // Call<ArrayList<Repo>> call = ApiAdapter.getInstance().getRepos();
                call.enqueue(this);
            }
        }
    }
}

package com.example.practicaretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.practicaretrofit.Adapter.ClickListener;
import com.example.practicaretrofit.Adapter.ReposAdapter;
import com.example.practicaretrofit.Model.Repo;
import com.example.practicaretrofit.databinding.ActivityMainBinding;

import java.util.ArrayList;

// https://developer.android.com/guide/topics/ui/floating-action-button
// https://material.io/components/buttons-floating-action-button/android#using-fabs
// https://material.io/resources/icons/?icon=autorenew&style=baseline
// https://stackoverflow.com/questions/25229124/format-instant-to-string
// https://stackoverflow.com/questions/19112357/java-simpledateformatyyyy-mm-ddthhmmssz-gives-timezone-as-ist
// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html
// https://www.baeldung.com/java-datetimeformatter
public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<ArrayList<Repo>> {
    private ActivityMainBinding binding;
    private ReposAdapter adapter;
    private ArrayList<Repo> repos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // binding.button.setOnClickListener(this);
        binding.fab.setOnClickListener(this);
        adapter = new ReposAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // binding.button.setOnClickListener(this);
        binding.fab.setOnClickListener(this);
        adapter = new ReposAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//manage click
        binding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, binding.recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                //Toast.makeText(getApplicationContext(), "Single Click on position        :" + position, Toast.LENGTH_SHORT).show();
                showMessage("Single Click on position:" + position);
                //Uri uri = Uri.parse((String) repos.get(position).getUrl());
                Uri uri = Uri.parse((String) repos.get(position).getHtmlUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    //Toast.makeText(getApplicationContext(), "No hay un navegador", Toast.LENGTH_SHORT).show();
                    showMessage("No hay un navegador");
            }
            @Override
            public void onLongClick(View view, int position) {
                //Toast.makeText(getApplicationContext(), "Long press on position :" + position, Toast.LENGTH_LONG).show();
                showMessage("Long press on position :" + position);
            }
        }));
//retrofit
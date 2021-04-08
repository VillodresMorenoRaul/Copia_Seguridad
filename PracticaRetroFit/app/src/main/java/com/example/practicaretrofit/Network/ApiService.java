package com.example.practicaretrofit.Network;

import com.example.practicaretrofit.Model.Repo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{username}/repos")
    Call<ArrayList<Repo>> listRepos(@Path("username") String username);
    // uso con servidor web local
    @GET("ficheros/repositorios.json")
    Call<ArrayList<Repo>> getRepos();
}

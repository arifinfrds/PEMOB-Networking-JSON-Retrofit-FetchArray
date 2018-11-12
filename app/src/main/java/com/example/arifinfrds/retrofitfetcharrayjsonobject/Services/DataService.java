package com.example.arifinfrds.retrofitfetcharrayjsonobject.Services;

import com.example.arifinfrds.retrofitfetcharrayjsonobject.Models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {

    @GET("/photos")
    Call<ArrayList<Photo>> fetchPhotos();

    @GET("/photos/{id}")
    Call<Photo> fetchPhoto(@Path("id") int photoId);


}

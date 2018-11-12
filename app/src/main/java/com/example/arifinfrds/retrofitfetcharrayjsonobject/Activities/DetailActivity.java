package com.example.arifinfrds.retrofitfetcharrayjsonobject.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifinfrds.retrofitfetcharrayjsonobject.Models.Photo;
import com.example.arifinfrds.retrofitfetcharrayjsonobject.R;
import com.example.arifinfrds.retrofitfetcharrayjsonobject.Services.DataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private ImageView detailImageView;
    private TextView detailTextView;

    private Bundle extras;

    private Retrofit retrofit;
    private DataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        detailImageView = findViewById(R.id.detailImageView);
        detailTextView = findViewById(R.id.detailTextView);

        setupRetrofit();

        extras = getIntent().getExtras();
        if (extras != null) {
            int photoId = extras.getInt("KEY_PHOTO_ID");
            Toast.makeText(this, "id foto : " + photoId, Toast.LENGTH_SHORT).show();

            attemptFetchPhoto(photoId);
        }

    }

    private void attemptFetchPhoto(int photoId) {
        Call<Photo> photoCall = dataService.fetchPhoto(photoId);
        photoCall.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                Photo photo = response.body();
                updateUI(photo);
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(Photo photo) {
        detailTextView.setText(photo.getTitle());
        Picasso.get().load(photo.getThumbnailUrl()).into(detailImageView);
    }

    private void setupRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        dataService = retrofit.create(DataService.class);
    }

}

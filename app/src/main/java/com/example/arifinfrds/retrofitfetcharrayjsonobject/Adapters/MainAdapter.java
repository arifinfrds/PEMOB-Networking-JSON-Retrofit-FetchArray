package com.example.arifinfrds.retrofitfetcharrayjsonobject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arifinfrds.retrofitfetcharrayjsonobject.Activities.DetailActivity;
import com.example.arifinfrds.retrofitfetcharrayjsonobject.Models.Photo;
import com.example.arifinfrds.retrofitfetcharrayjsonobject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<Photo> photos;
    private Context context;

    public MainAdapter(ArrayList<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Photo photo = photos.get(position);
        holder.textView.setText(photo.getTitle());
        Picasso.get().load(photo.getThumbnailUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetailActivity(photo.getId());
            }
        });

    }

    private void navigateToDetailActivity(int photoId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("KEY_PHOTO_ID", photoId);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}

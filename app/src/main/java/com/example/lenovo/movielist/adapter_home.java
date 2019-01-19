package com.example.lenovo.movielist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter_home extends RecyclerView.Adapter<adapter_home.Holder> {
    Context context;
    ArrayList<list_movie>arrayList;
    public adapter_home(Context context, ArrayList<list_movie>arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_movie,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final list_movie list_movie = arrayList.get(i);
        holder.textView.setText(list_movie.getTitle());
        Picasso.get().load(list_movie.getImage_url()).placeholder(R.color.grey).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = list_movie.getId();
                final String title = list_movie.getTitle();
                final String image_url = list_movie.getImage_url();
                final String overview = list_movie.getOverview();
                final String release_date = list_movie.getRelease_date();
                Intent intent = new Intent(context,details.class);
                intent.putExtra("id_movie",id);
                intent.putExtra("title",title);
                intent.putExtra("image_url",image_url);
                intent.putExtra("overview",overview);
                intent.putExtra("release_date",release_date);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_title);
        }
    }
}

package com.example.lenovo.movielist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class details extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView_title,textView_overview,textView_releasedate;
    private int id;
    private String title,image_url,overview,release_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.image_view);
        textView_title = findViewById(R.id.text_title);
        textView_overview = findViewById(R.id.text_overview);
        textView_releasedate = findViewById(R.id.text_release_date);
        id = getIntent().getExtras().getInt("id_movie");
        title = getIntent().getExtras().getString("title");
        image_url = getIntent().getExtras().getString("image_url");
        overview = getIntent().getExtras().getString("overview");
        release_date = getIntent().getExtras().getString("release_date");
        Picasso.get().load(image_url).placeholder(R.color.grey).into(imageView);
        textView_title.setText(title);
        textView_releasedate.setText(release_date);
        textView_overview.setText(overview);
    }
}

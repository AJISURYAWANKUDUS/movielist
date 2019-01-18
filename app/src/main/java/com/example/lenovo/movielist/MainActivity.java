package com.example.lenovo.movielist;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JsonObjectRequest stringRequest;
    private RequestQueue requestQueue;
    private ArrayList<list_movie>arrayList;
    private list_movie list_movie;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
//        swipeRefreshLayout= findViewById(R.id.swipe_layout);
        //swipeRefreshLayout.setOnRefreshListener(this);
        load_movie();

    }

    private void load_movie() {
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        stringRequest = new JsonObjectRequest(Request.Method.GET, config_url.url + "list_movie.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Result");
                    arrayList = new ArrayList<list_movie>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list_movie = new list_movie();
                        list_movie.setTitle(jsonArray.getJSONObject(i).getString("title"));
                        Log.d("error", "onResponse: "+list_movie.getTitle());
                        arrayList.add(list_movie);
                    }
                    adapter_home adapter_home = new adapter_home(MainActivity.this,arrayList);
                    recyclerView.setAdapter(adapter_home);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }
}
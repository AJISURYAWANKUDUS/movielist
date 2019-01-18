package com.example.lenovo.movielist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class login extends AppCompatActivity {
    private EditText editText_email, editText_password;
    private Button button_login;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_email = findViewById(R.id.edit_text_login_email);
        editText_password = findViewById(R.id.edit_text_login_password);
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editText_email.getText().toString();
                password = editText_password.getText().toString();
                if (email.isEmpty() && password.isEmpty()) {
                    editText_email.setError("email cannot empty");
                    editText_password.setError("password cannot empty");
                } else {
                    new login_execute().execute();
                }
            }
        });
    }

    public class login_execute extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (jsonObject != null) {
                try {
                    JSONObject result = jsonObject.getJSONObject("Result");
                    String hasil = result.getString("Sukses");
                    if (hasil.equals("true")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("login_email", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        editor.putString("email", email);
                        editor.apply();
                        startActivity(intent);
                        Log.d("eror if", "onPostExecute: ");
                    } else {
                        Log.d("eror else", "onPostExecute: ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("TAG", "onPostExecute: " + "json");
            }
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject jsonObject;
            try {
                String url = config_url.url + "login.php?username=" + email + "&password=" + password;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                jsonObject = null;
            }
            return jsonObject;
        }
    }

}

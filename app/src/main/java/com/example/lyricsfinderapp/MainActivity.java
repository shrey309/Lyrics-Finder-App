package com.example.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{

    EditText ArtistName,SongName;
    Button GetMeLyrics;
    TextView txtLyrics;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArtistName=findViewById(R.id.edtArtistName);
        SongName=findViewById(R.id.edtSongName);
        GetMeLyrics=findViewById(R.id.btnGetLyrics);
        txtLyrics=findViewById(R.id.txtLyrics);





             GetMeLyrics.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {


                     if (ArtistName.getText().toString().isEmpty() && SongName.getText().toString().isEmpty()) {

                         Toast.makeText(MainActivity.this, "Song name and artist name need to be filled", Toast.LENGTH_SHORT).show();

                     } else {


                         url = "https://api.lyrics.ovh/v1/" + ArtistName.getText().toString() + "/" + SongName.getText().toString();
                         url.replaceAll(" ", "%20");

                         RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                             @Override
                             public void onResponse(JSONObject response) {

                                 try {
                                     txtLyrics.setText(response.getString("lyrics"));
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }

                             }
                         }, new Response.ErrorListener() {
                             @Override
                             public void onErrorResponse(VolleyError error) {

                                 Toast.makeText(getApplicationContext(), "Please provide correct details", Toast.LENGTH_SHORT).show();


                             }

                         });

                         requestQueue.add(jsonObjectRequest);

                     }

                 }
             });











         }



    }





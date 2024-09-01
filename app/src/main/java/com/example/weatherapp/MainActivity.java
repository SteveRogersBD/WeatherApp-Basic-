package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText longi,lat;
    Button search;
    TextView show;
    ApiInterface apiInterface;
    String ans;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longi = findViewById(R.id.longitude);
        lat = findViewById(R.id.latitude);
        search = findViewById(R.id.search);
        show = findViewById(R.id.weather);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button clicked!!!",
                        Toast.LENGTH_SHORT).show();

                double longitude = Double.parseDouble(longi.getText().toString());
                double latitude = Double.parseDouble(lat.getText().toString());

                apiInterface = RetrofitInstance.getClient().create(ApiInterface.class);
                apiInterface.getWeather(latitude,longitude,getString(R.string.apiKey)).
                        enqueue(new Callback<WeatherResponse>() {
                            @Override
                            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    WeatherResponse a = response.body();
                                    // Ensure that `a.weather` and `a.weather.get(0)` are not null
                                    if (a.weather != null && !a.weather.isEmpty()) {
                                        ans = "City: " + a.name + " ,temp: " + a.main.temp + " ,des: " + a.weather.get(0).description;
                                        show.setText(ans);
                                    } else {
                                        Toast.makeText(MainActivity.this, "No weather data available", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
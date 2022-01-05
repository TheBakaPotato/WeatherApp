package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_GetCityID, btn_getWeatherByCityID, btn_getWeatherByCityName;
    EditText et_DataInput;
    ListView lv_WeatherReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_GetCityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByCityID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByCityName = findViewById(R.id.btn_getWeatherByCityName);
        et_DataInput = findViewById(R.id.et_dataInput);
        lv_WeatherReport = findViewById(R.id.lv_weatherReports);
        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        btn_GetCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                weatherDataService.getCityID(et_DataInput.getText().toString(), new WeatherDataService.VolleyCallBack() {
                    @Override
                    public void onSuccess(String cityID) {
                        Toast.makeText(MainActivity.this, "Third toast CityID Test "+cityID, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        btn_getWeatherByCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                weatherDataService.getCityForecastByID(et_DataInput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {
                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_WeatherReport.setAdapter(arrayAdapter);

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btn_getWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                weatherDataService.getCityForecastByName(et_DataInput.getText().toString(), new WeatherDataService.ForeCastByNameResponse() {
                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_WeatherReport.setAdapter(arrayAdapter);

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });
//                weatherDataService.getCityID(et_DataInput.getText().toString(), new WeatherDataService.VolleyCallBack() {
//                    @Override
//                    public void onSuccess(String cityID) {
//                        weatherDataService.getCityForecastByID(cityID, new WeatherDataService.ForeCastByIDResponse() {
//                            @Override
//                            public void onResponse(List<WeatherReportModel> weatherReportModels) {
//                                //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
//                                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
//                                lv_WeatherReport.setAdapter(arrayAdapter);
//
//                            }
//
//                            @Override
//                            public void onError(String message) {
//                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });
    }
}
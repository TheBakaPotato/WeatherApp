package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        btn_GetCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String cityName = et_DataInput.getText().toString();
                String url ="https://www.metaweather.com/api/location/search/?query="+cityName;
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject cityInfo = response.getJSONObject(0);
                                    String cityID = cityInfo.getString("woeid");
                                    Toast.makeText(MainActivity.this, cityID.toString(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Conserta essa fita parcero", Toast.LENGTH_SHORT).show();

                    }
                });

                // Access the RequestQueue through your singleton class.
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonArrayRequest);

            }
        });
        btn_getWeatherByCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://www.metaweather.com/api/location/search/?query=london";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject cityInfo = response.getJSONObject(0);
                                    String cityName = cityInfo.getString("title");
                                    Toast.makeText(MainActivity.this, cityName.toString(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Conserta essa fita parcero", Toast.LENGTH_SHORT).show();

                    }
                });

                // Access the RequestQueue through your singleton class.
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonArrayRequest);
                Toast.makeText(MainActivity.this, "Botão 2", Toast.LENGTH_SHORT).show();
            }
        });
        btn_getWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String texto = String.valueOf(et_DataInput.getText());
                    Toast.makeText(MainActivity.this, "Você digitou "+texto, Toast.LENGTH_SHORT).show();
                }catch(Exception e){

                }
            }
        });
    }
}
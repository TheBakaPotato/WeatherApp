package com.example.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    Context context;
    String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }
    public interface VolleyCallBack {
        void onSuccess(String cityID);
        void onError(String message);
    }
    public void getCityID(String cityName, final VolleyCallBack volleyCallBack) {
        String url = QUERY_FOR_CITY_ID+cityName;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        cityID = "";
                        //Toast.makeText(context, "First Toast"+response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject cityInfo = response.getJSONObject(0);
                            cityID = cityInfo.getString("woeid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(context, "Second Toast"+cityID.toString(), Toast.LENGTH_SHORT).show();
                        volleyCallBack.onSuccess(cityID);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Conserta essa fita parcero", Toast.LENGTH_SHORT).show();
                volleyCallBack.onError("Algo deu errado");
            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        //return cityID;
    }
    public void getCityForecastByID(String cityID, final VolleyCallBack volleyCallBack ){
        List<WeatherReportModel> report = new ArrayList<>();
    }
//    public List<WeatherReportModel> getCityForecastByName(String cityName){
//
//    }
}

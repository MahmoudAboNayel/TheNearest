package com.example.it.thenearest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView places ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        places = (ListView)findViewById(R.id.placeList);
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyDi9JdUYE28KGzwm5t-dLONa4zIaVne6jc";
        excuteWebService(url);
    }
    void excuteWebService(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    final PlaceModel[] PLACE_MODEL;
                    PLACE_MODEL = new Gson().fromJson(jsonArray.toString(),PlaceModel[].class);
                    for (int i = 0; i <PLACE_MODEL.length ; i++) {
                        Log.d("nayel", "On response " +PLACE_MODEL[i].getName());
                    }
                    PlaceAdapter placeAdapter = new PlaceAdapter(MainActivity.this , PLACE_MODEL);
                    places.setAdapter(placeAdapter);
                    places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getApplicationContext(),PLACE_MODEL[position].getName(),Toast.LENGTH_LONG);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    queue.add(stringRequest);
    }
}

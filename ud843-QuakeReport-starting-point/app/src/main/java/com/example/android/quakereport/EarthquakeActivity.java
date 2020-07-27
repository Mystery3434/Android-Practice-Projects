/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";
    private LocationItemAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        // Create a fake list of earthquake locations.
        //ArrayList<LocationItem> earthquakes = QueryUtils.extractEarthquakes(USGS_REQUEST_URL);
//        earthquakes.add(new LocationItem("7.2","San Francisco","Feb 2, 2016"));
//        earthquakes.add(new LocationItem("6.1","London","Feb 2, 2016"));
//        earthquakes.add(new LocationItem("3.9","Tokyo","Feb 2, 2016"));
//        earthquakes.add(new LocationItem("5.4","Mexico City","Feb 2, 2016"));
//        earthquakes.add(new LocationItem("2.8","Moscow","Feb 2, 2016"));
//        earthquakes.add(new LocationItem("4.9","Rio de Janeiro","Feb 2, 2016"));
//        earthquakes.add(new LocationItem("1.6","Paris","Feb 2, 2016"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        mAdapter = new LocationItemAdapter(this, new ArrayList<LocationItem>());
        earthquakeListView.setAdapter(mAdapter);

        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute(USGS_REQUEST_URL);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                LocationItem currentEarthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });


    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<LocationItem>> {

        @Override
        protected List<LocationItem> doInBackground(String... urls) {
            if (urls[0] == null || urls.length < 1)
                return null;
            List<LocationItem> earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<LocationItem> earthquakes) {
            mAdapter.clear();

            if (earthquakes != null && !earthquakes.isEmpty()) {
                mAdapter.addAll(earthquakes);

            }
        }


    }
}

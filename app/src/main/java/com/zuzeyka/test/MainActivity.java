package com.zuzeyka.test;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = new GraphView(this);
        LinearLayout layout = findViewById(R.id.graph_layout);
        layout.addView(graphView);

        // Загружаем исторические данные о курсе биткоина
        new FetchBitcoinPriceHistoryTask().execute();
    }

    public void updateTotalPrice() {

    }

    private class FetchBitcoinPriceHistoryTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String result = null;
            try {
                URL url = new URL("https://api.coindesk.com/v1/bpi/historical/close.json?start=2017-02-01&end=2022-02-01");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                reader.close();
                result = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject bpiObject = jsonObject.getJSONObject("bpi");
                    JSONArray dateArray = bpiObject.names();

                    DataPoint[] dataPoints = new DataPoint[dateArray.length()];
                    for (int i = 0; i < dateArray.length(); i++) {
                        String date = dateArray.getString(i);
                        double price = bpiObject.getDouble(date);
                        dataPoints[i] = new DataPoint(i, price);
                    }

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
                    graphView.addSeries(series);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




package com.martin.cal.chartyourlikes.charts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.martin.cal.chartyourlikes.R;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class YearBarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_bar_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BarChart chart = (BarChart) findViewById(R.id.chart);


        List<BarEntry> entries = processData(Movies.movies);

        BarDataSet set = new BarDataSet(entries, "Movies By Year");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.getXAxis().setGranularity(1f);
//        chart.getAxisLeft().setGranularity(1f);
//        chart.getAxisRight().setGranularity(1f);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh
    }


    private List<BarEntry> processData(Movies movies)
    {
        List<BarEntry> results = new ArrayList<>();
        HashMap<Integer, Integer> moviesByYear = new HashMap<>();

        for (int i = 0; i < movies.movieData.length(); i++)
        {
            try {
                //Just grab the year
                Integer date = Integer.parseInt(movies.movieData.getJSONObject(i).getString("release_date").substring(0,4));

                if (date != null)
                {
                    Integer current = moviesByYear.get(date);
                    if (current == null)
                        current = 0;
                    moviesByYear.put(date, current + 1);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        Iterator it = moviesByYear.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
            System.out.println(entry.getKey() + " has " + entry.getValue());
            results.add(new BarEntry(entry.getKey(), entry.getValue()));
        }

        return results;
    }

}

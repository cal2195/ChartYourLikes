package com.martin.cal.chartyourlikes.charts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.martin.cal.chartyourlikes.R;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONException;

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
        // Add up and icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        if (Movies.movies.movieData != null) {

            List<BarEntry> entries = processData(Movies.movies);

            if (entries != null) {

                BarDataSet set = new BarDataSet(entries, "Movies By Year");
                set.setColors(ColorTemplate.VORDIPLOM_COLORS);
                set.setHighlightEnabled(false);

                Description description = new Description();
                description.setText("Total Films Released Each Year");
                chart.setDescription(description);

                BarData data = new BarData(set);
                data.setBarWidth(0.9f); // set custom bar width
                data.setDrawValues(false);

                chart.setData(data);
                chart.getXAxis().setGranularity(1f);
                chart.getAxisLeft().setGranularity(1f);
                chart.getAxisRight().setGranularity(1f);

                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish(); // Go up to parent activity on up button
        return true;
    }

    protected List<BarEntry> processData(Movies movies)
    {
        if (movies == null)
            return null; // No data

        List<BarEntry> results = new ArrayList<>();
        HashMap<Integer, Integer> moviesByYear = new HashMap<>();

        for (int i = 0; i < movies.movieData.length(); i++)
        {
            try {
                // Just grab the year (first 4 chars)
                Integer date = Integer.parseInt(movies.movieData.getJSONObject(i).getString("release_date").substring(0,4));

                if (date != null)
                {
                    Integer current = moviesByYear.get(date);
                    if (current == null)
                        current = 0;
                    moviesByYear.put(date, current + 1);
                }
            } catch (JSONException e) {
                // Movie missing date field - ignore
            }
        }

        int highestYear = 0;
        Iterator it = moviesByYear.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
            System.out.println(entry.getKey() + " has " + entry.getValue());
            results.add(new BarEntry(entry.getKey(), entry.getValue()));

            // Update highest year
            highestYear = ((highestYear < entry.getKey()) ? entry.getKey() : highestYear);
        }

        // Did we have data?
        if (highestYear == 0)
        {
            return null;
        }

        results.add(new BarEntry(highestYear + 1,0)); // Also include a 0 to fix chart bug

        return results;
    }

}

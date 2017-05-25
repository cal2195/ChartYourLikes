package com.martin.cal.chartyourlikes.charts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.martin.cal.chartyourlikes.R;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActorBarChartActivity extends AppCompatActivity {

    String[] topActors = { "Tom Hanks", "Kevin Spacey", "Morgan Freeman", "Leonardo DiCaprio", "Christian Bale",
                           "Denzel Washington", "Robert De Niro", "Jack Nicholson", "Liam Neeson", "Al Pacino" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_bar_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Add up button and icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);

        // If we have data
        if (Movies.movies.movieData != null) {

            List<BarEntry> entries = processData(Movies.movies);

            if (entries != null) {
                BarDataSet set = new BarDataSet(entries, "Top 10 Actors");
                set.setColors(ColorTemplate.VORDIPLOM_COLORS);

                BarData data = new BarData(set);
                data.setBarWidth(0.5f); // set custom bar width
                data.setDrawValues(false);

                Description description = new Description();
                description.setText("Amount Of Movies Each Actor Appears In");
                chart.setDescription(description);

                // Actors names on axis
                IAxisValueFormatter formatter = new IAxisValueFormatter() {

                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return topActors[(int) value];
                    }
                };

                XAxis xAxis = chart.getXAxis();
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);

                chart.getAxisRight().setGranularity(1f);
                chart.getAxisLeft().setGranularity(1f);
                chart.getXAxis().setLabelCount(topActors.length, false);

                chart.setData(data);
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

    public List<BarEntry> processData(Movies movies)
    {
        if (movies == null)
            return null; // No data

        List<BarEntry> results = new ArrayList<>();
        HashMap<String, Integer> actorMap = new HashMap<>();

        for (int i = 0; i < movies.movieData.length(); i++)
        {
            try {
                // Get all starring actors
                String actors = movies.movieData.getJSONObject(i).getString("starring");

                // See if they're in our top list
                for (String actor : topActors)
                {
                    if (actors.contains(actor))
                    {
                        Integer current = actorMap.get(actor);
                        if (current == null)
                            current = 0;
                        actorMap.put(actor, current + 1);
                    }
                }
            } catch (JSONException e) {
                // Movie missing starring actors field - ignore
            }
        }

        for (int i = 0; i < topActors.length; i++)
        {
            if (actorMap.containsKey(topActors[i]))
                results.add(new BarEntry(i, actorMap.get(topActors[i])));
            else
                results.add(new BarEntry(i, 0)); // Add actor with 0 films
        }

        return results;
    }


}

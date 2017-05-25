package com.martin.cal.chartyourlikes.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.martin.cal.chartyourlikes.R;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StudioPieChartActivity extends AppCompatActivity {

    Description description = new Description();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_pie_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PieChart chart = (PieChart) findViewById(R.id.chart);

        description.setText("Only including studios with more than two films!");
        chart.setDescription(description);

        if (Movies.movies.movieData != null) {

            List<PieEntry> entries = processData(Movies.movies);

            PieDataSet set = new PieDataSet(entries, "Movies by Studio");
            set.setColors(ColorTemplate.VORDIPLOM_COLORS);
            PieData data = new PieData(set);
            chart.setEntryLabelColor(Color.BLACK);
            chart.setData(data);
            chart.invalidate(); // refresh
        }
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }

    private List<PieEntry> processData(Movies movies)
    {
        List<PieEntry> results = new ArrayList<>();
        HashMap<String, Integer> studios = new HashMap<>();

        for (int i = 0; i < movies.movieData.length(); i++)
        {
            try {
                //Just grab the year
                String studio = movies.movieData.getJSONObject(i).getString("studio").trim();

                if (studio != null)
                {
                    Integer current = studios.get(studio);
                    if (current == null)
                        current = 0;
                    studios.put(studio, current + 1);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        Iterator it = studios.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
            System.out.println(entry.getKey() + " has " + entry.getValue());
            if (entry.getValue() > 2)
            results.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        return results;
    }

}

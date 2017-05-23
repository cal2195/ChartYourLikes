package com.martin.cal.chartyourlikes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.martin.cal.chartyourlikes.charts.YearBarChartActivity;
import com.martin.cal.chartyourlikes.data.Movies;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartListFragment extends Fragment {

    enum Charts { YEAR_BARCHART }

    Bundle movieBundle;

    public ChartListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChartListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartListFragment newInstance() {
        ChartListFragment fragment = new ChartListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chart_list, container, false);

        Movies.movies = new Movies();
        Movies.movies.fetchMovies();

        Button moviesByYearButton = (Button) rootView.findViewById(R.id.moviesByYear);
        moviesByYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChart(Charts.YEAR_BARCHART);
            }
        });

        return rootView;
    }

    private void showChart(Charts chart)
    {
        switch (chart)
        {
            case YEAR_BARCHART:
                Intent intent = new Intent(getContext(), YearBarChartActivity.class);
                startActivity(intent);
                break;
        }
    }

}

package com.martin.cal.chartyourlikes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.martin.cal.chartyourlikes.charts.ActorBarChartActivity;
import com.martin.cal.chartyourlikes.charts.StudioPieChartActivity;
import com.martin.cal.chartyourlikes.charts.YearBarChartActivity;

public class ChartListFragment extends Fragment {

    enum Charts { YEAR_BARCHART, STUDIO_PIECHART, ACTOR_BARCHART }

    public ChartListFragment() {
        // Required empty public constructor
    }

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

        Button moviesByYearButton = (Button) rootView.findViewById(R.id.moviesByYear);
        moviesByYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChart(Charts.YEAR_BARCHART);
            }
        });

        Button moviesByStudioButton = (Button) rootView.findViewById(R.id.moviesByStudio);
        moviesByStudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChart(Charts.STUDIO_PIECHART);
            }
        });

        Button moviesByTopActorsButton = (Button) rootView.findViewById(R.id.moviesByActors);
        moviesByTopActorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChart(Charts.ACTOR_BARCHART);
            }
        });

        return rootView;
    }

    private void showChart(Charts chart)
    {
        Intent intent;
        switch (chart)
        {
            case YEAR_BARCHART:
                intent = new Intent(getContext(), YearBarChartActivity.class);
                startActivity(intent);
                break;

            case STUDIO_PIECHART:
                intent = new Intent(getContext(), StudioPieChartActivity.class);
                startActivity(intent);
                break;

            case ACTOR_BARCHART:
                intent = new Intent(getContext(), ActorBarChartActivity.class);
                startActivity(intent);
                break;
        }
    }
}

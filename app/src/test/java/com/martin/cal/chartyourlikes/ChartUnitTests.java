package com.martin.cal.chartyourlikes;

import com.martin.cal.chartyourlikes.charts.YearBarChartActivity;
import com.martin.cal.chartyourlikes.data.Movies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ChartUnitTests {

    @Mock
    Movies movies;

    @Test
    public void testYearBarChartDataNull()
    {
        YearBarChartActivity yearBarChartActivity = new YearBarChartActivity();
        assertThat(yearBarChartActivity.processData(null), is(nullValue()));
    }
}
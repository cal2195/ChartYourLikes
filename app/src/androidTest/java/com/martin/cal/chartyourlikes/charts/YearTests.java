package com.martin.cal.chartyourlikes.charts;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.martin.cal.chartyourlikes.TestData;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class YearTests {

    static {
        // Setup movies object dependency
        Movies.movies = new Movies();
    }

    @Rule
    public ActivityTestRule<YearBarChartActivity> rule = new ActivityTestRule<>(YearBarChartActivity.class);

    @Test
    public void testYearBarChartData() throws JSONException {

        Movies.movies.movieData = new JSONArray(TestData.LOTS_OF_FILMS);

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies).toString(), is(TestData.LOTS_OF_YEARS));
    }

    @Test
    public void testYearBarChartDataOne() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"release_date\":\"20070629\",\"id\":\"17377140236\"}]");

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 2007.0 y: 1.0, Entry, x: 2008.0 y: 0.0]"));
    }

    @Test
    public void testYearBarChartDataMinimal() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"release_date\":\"20110629\"}]");

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 2011.0 y: 1.0, Entry, x: 2012.0 y: 0.0]"));
    }

    @Test
    public void testYearBarChartDataMixed() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"release_date\":\"20070629\",\"id\":\"17377140236\"},{\"name\":\"Year One\",\"release_date\":\"27th August 2012\",\"id\":\"1737712136\"}]");

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 2007.0 y: 1.0, Entry, x: 2008.0 y: 0.0]"));
    }

    @Test
    public void testYearBarChartDataYearsOnly() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"release_date\":\"2007\",\"id\":\"17377140236\"},{\"name\":\"Year One\",\"release_date\":\"2008\",\"id\":\"1737712136\"}]");

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 2007.0 y: 1.0, Entry, x: 2008.0 y: 1.0, Entry, x: 2009.0 y: 0.0]"));
    }

    @Test
    public void testYearBarChartDataNone() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"id\":\"17377140236\"}]");

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies), is(nullValue()));
    }

    @Test
    public void testYearBarChartDataIncorrect() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"release_date\":\"problems\",\"id\":\"17377140236\"}]");

        YearBarChartActivity yearBarChartActivity = rule.getActivity();

        assertThat(yearBarChartActivity.processData(Movies.movies), is(nullValue()));
    }


}

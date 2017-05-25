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
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class StudioTests {

    static {
        // Setup movies object dependency
        Movies.movies = new Movies();
    }

    @Rule
    public ActivityTestRule<StudioPieChartActivity> rule = new ActivityTestRule<>(StudioPieChartActivity.class);

    @Test
    public void testActorBarChartData() throws JSONException {

        Movies.movies.movieData = new JSONArray(TestData.LOTS_OF_FILMS);

        StudioPieChartActivity studioPieChartActivity = rule.getActivity();

        assertThat(studioPieChartActivity.processData(Movies.movies).toString(), is(TestData.LOTS_OF_STUDIOS));
    }


    @Test
    public void testActorBarChartDataOneResult() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"studio\":\"Disney\",\"id\":\"17377140236\"},{\"name\":\"Frozen\",\"studio\":\"Disney\",\"id\":\"17377143336\"}]");

        StudioPieChartActivity studioPieChartActivity = rule.getActivity();

        assertThat(studioPieChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 0.0 y: 2.0]"));
    }

    @Test
    public void testActorBarChartDataMinimal() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"studio\":\"Disney\"},{\"studio\":\"Disney\"}]");

        StudioPieChartActivity studioPieChartActivity = rule.getActivity();

        assertThat(studioPieChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 0.0 y: 2.0]"));
    }

    @Test
    public void testActorBarChartDataMixed() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"studio\":\"Disney\",\"id\":\"17377140236\"},{\"name\":\"Year One\",\"studio\":\"DC\",\"id\":\"1737712136\"},{\"name\":\"Batman\",\"studio\":\"DC\"},{\"name\":\"Frozen\",\"studio\":\"Disney\"}]");

        StudioPieChartActivity studioPieChartActivity = rule.getActivity();

        assertThat(studioPieChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 0.0 y: 2.0, Entry, x: 0.0 y: 2.0]"));
    }

    @Test
    public void testActorBarChartDataNone() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"id\":\"17377140236\"}]");

        StudioPieChartActivity studioPieChartActivity = rule.getActivity();

        assertThat(studioPieChartActivity.processData(Movies.movies).toString(), is("[]"));
    }

    @Test
    public void testActorBarChartDataIncorrect() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"studio\":\"\",\"id\":\"17377140236\"}]");

        StudioPieChartActivity studioPieChartActivity = rule.getActivity();

        assertThat(studioPieChartActivity.processData(Movies.movies).toString(), is("[]"));
    }


}

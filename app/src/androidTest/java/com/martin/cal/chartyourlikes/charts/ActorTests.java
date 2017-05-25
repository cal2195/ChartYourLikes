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
public class ActorTests {

    static {
        // Setup movies object dependency
        Movies.movies = new Movies();
    }

    @Rule
    public ActivityTestRule<ActorBarChartActivity> rule = new ActivityTestRule<>(ActorBarChartActivity.class);

    @Test
    public void testActorBarChartData() throws JSONException {

        Movies.movies.movieData = new JSONArray(TestData.LOTS_OF_FILMS);

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.LOTS_OF_ACTORS));
    }


        @Test
    public void testActorBarChartDataOne() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"starring\":\"Tom Hanks\",\"release_date\":\"20070629\",\"id\":\"17377140236\"}]");

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.JUST_HANKS));
    }

    @Test
    public void testActorBarChartDataMinimal() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"starring\":\"Denzel Washington\"}]");

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.JUST_WASHINGTON));
    }

    @Test
    public void testActorBarChartDataMixed() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"starring\":\"Tom Hanks\",\"id\":\"17377140236\"},{\"name\":\"Actor One\",\"release_date\":\"27th August 2012\",\"id\":\"1737712136\"}]");

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.JUST_HANKS));
    }

    @Test
    public void testActorBarChartDataActorsAndRoles() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"starring\":\"Tom Hanks (Some Role)\",\"id\":\"17377140236\"}]");

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.JUST_HANKS));
    }

    @Test
    public void testActorBarChartDataNone() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"id\":\"17377140236\"}]");

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.ACTORS_ALL_ZERO));
    }

    @Test
    public void testActorBarChartDataIncorrect() throws JSONException {

        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"starring\":\"1234\",\"id\":\"17377140236\"}]");

        ActorBarChartActivity actorBarChartActivity = rule.getActivity();

        assertThat(actorBarChartActivity.processData(Movies.movies).toString(), is(TestData.ACTORS_ALL_ZERO));
    }


}

package com.martin.cal.chartyourlikes.data;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by cal on 23/05/17.
 */

public class Movies {
    //Singleton
    public static Movies movies;

    Bundle movieFields;
    public JSONArray movieData;

    public Movies()
    {
        movieFields = new Bundle();
        movieFields.putString("fields", "name,genre,awards,starring,release_date,studio,directed_by,produced_by,id");
    }

    public void processMovies(JSONArray movies)
    {
        this.movieData = movies;
    }

    public void fetchMovies()
    {
        /* make the API call */
        GraphRequest request = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + Profile.getCurrentProfile().getId() + "/movies",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        System.out.println(response.getJSONObject());
                        try {
                            processMovies(response.getJSONObject().getJSONArray("data"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        request.setParameters(movieFields);
        request.executeAsync();
    }
}

package com.martin.cal.chartyourlikes.data;

import android.content.Context;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by cal on 23/05/17.
 */

public class Movies {
    //Singleton for easy access
    public static Movies movies;

    public Bundle movieFields;
    public JSONArray movieData;

    public Movies()
    {
        setupBundle();
    }

    public void setupBundle()
    {
        // Setup field for request
        movieFields = new Bundle();
        movieFields.putString("fields", "name,genre,awards,starring,release_date,studio,directed_by,produced_by,id");
    }

    public void processMovies(JSONArray movies)
    {
        this.movieData = movies;
    }

    public void fetchMovies(final Context context)
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
                            // No internet connection
                            SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Connection Error!");
                            dialog.setContentText("It looks like you have no internet connection! :(");
                            dialog.show();
                        }
                    }
                }
        );
        // Set the request fields
        request.setParameters(movieFields);
        request.executeAsync();
    }
}

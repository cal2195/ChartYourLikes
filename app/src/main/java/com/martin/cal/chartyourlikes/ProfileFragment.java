package com.martin.cal.chartyourlikes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.ProfilePictureView;
import com.martin.cal.chartyourlikes.data.Movies;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // If our profile is ready
        if (Profile.getCurrentProfile() != null)
        {
            loadProfile(rootView);
        }

        // Otherwise update it later, or if it changes
        ProfileTracker mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                    loadProfile(rootView);
            }
        };
        mProfileTracker.startTracking();

        return rootView;
    }

    public void loadProfile(View rootView)
    {
        // Load the profile picture
        ProfilePictureView profilePictureView = (ProfilePictureView) rootView.findViewById(R.id.profilePicture);
        profilePictureView.setPresetSize(ProfilePictureView.LARGE);
        profilePictureView.setProfileId(Profile.getCurrentProfile().getId());

        // Update name
        TextView nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
        nameTextView.setText(Profile.getCurrentProfile().getName());

        // Fetch other details
        fetchProfileInfo(rootView);

        // Fetch and process movie likes
        Movies.movies = new Movies();
        Movies.movies.fetchMovies(getContext());
    }

    public void fetchProfileInfo(final View rootView)
    {
        // Info we would like
        Bundle profileFields = new Bundle();
        profileFields.putString("fields", "name,age_range,birthday,email,gender,location,locale,relationship_status,timezone,website,id");

        /* make the API call */
        GraphRequest request = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + Profile.getCurrentProfile().getId(),
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        System.out.println(response.getJSONObject());
                        try {
                            String gender = "Gender: " + response.getJSONObject().getString("gender");
                            TextView genderView = (TextView) rootView.findViewById(R.id.genderTextView);
                            genderView.setText(gender);
                        } catch (Exception e) {
                            System.out.println("Gender not found!");
                        }
                        try {
                            String email = response.getJSONObject().getString("email");
                            TextView emailView = (TextView) rootView.findViewById(R.id.emailTextView);
                            emailView.setText(email);
                        } catch (Exception e) {
                            System.out.println("Email not found!");
                        }
                        try {
                            String locale = "Locale: " + response.getJSONObject().getString("locale");
                            TextView localeTextView = (TextView) rootView.findViewById(R.id.localeTextView);
                            localeTextView.setText(locale);
                        } catch (Exception e) {
                            System.out.println("Locale not found!");
                        }
                        try {
                            String timezone = "Timezone: UTC " + response.getJSONObject().getString("timezone");
                            TextView timezoneTextView = (TextView) rootView.findViewById(R.id.timezoneTextView);
                            timezoneTextView.setText(timezone);
                        } catch (Exception e) {
                            System.out.println("Timezone not found!");
                        }
                    }
                }
        );
        request.setParameters(profileFields);
        request.executeAsync();
    }
}

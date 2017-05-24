package com.martin.cal.chartyourlikes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.ProfilePictureView;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        if (Profile.getCurrentProfile() != null)
        {
            loadProfile(rootView);
        }

        // When the profile changes
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
        Movies.movies.fetchMovies();
    }

    public void fetchProfileInfo(final View rootView)
    {
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
                        } catch (JSONException e) {
                            System.out.println("Gender not found!");
                        }
                        try {
                            String email = response.getJSONObject().getString("email");
                            TextView emailView = (TextView) rootView.findViewById(R.id.emailTextView);
                            emailView.setText(email);
                        } catch (JSONException e) {
                            System.out.println("Email not found!");
                        }
                        try {
                            String locale = "Locale: " + response.getJSONObject().getString("locale");
                            TextView localeTextView = (TextView) rootView.findViewById(R.id.localeTextView);
                            localeTextView.setText(locale);
                        } catch (JSONException e) {
                            System.out.println("Locale not found!");
                        }
                        try {
                            String timezone = "Timezone: UTC " + response.getJSONObject().getString("timezone");
                            TextView timezoneTextView = (TextView) rootView.findViewById(R.id.timezoneTextView);
                            timezoneTextView.setText(timezone);
                        } catch (JSONException e) {
                            System.out.println("Timezone not found!");
                        }
                    }
                }
        );
        request.setParameters(profileFields);
        request.executeAsync();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

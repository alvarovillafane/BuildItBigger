package com.udacity.gradle.builditbigger.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alvaro.jokedisplay.JokeDisplayActivity;
import com.alvarovm.provider.JokeProvider;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnTellJoke = (Button) root.findViewById(R.id.tell_joke_btn);
        btnTellJoke.setOnClickListener(this);

        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Manfred"));

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tell_joke_btn:
                tellJoke(v);
                break;

        }
    }

    public void tellJoke(View view){
        JokeProvider jokeProvider = new JokeProvider();
        Intent displayIntent = new Intent(getActivity(),JokeDisplayActivity.class);
        displayIntent.putExtra(Intent.EXTRA_TEXT, jokeProvider.getJoke());
        startActivity(displayIntent);


    }

}

package com.udacity.gradle.builditbigger.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alvaro.jokedisplay.JokeDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, EndpointsAsyncTask.TaskCallback{

    public MainActivityFragment() {
    }

    PublisherInterstitialAd mPublisherInterstitialAd;
    String joke;
    private ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner = (ProgressBar) root.findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        Button btnTellJoke = (Button) root.findViewById(R.id.tell_joke_btn);
        btnTellJoke.setOnClickListener(this);


        mPublisherInterstitialAd = new PublisherInterstitialAd(getActivity());
        mPublisherInterstitialAd.setAdUnitId(getString(R.string.intertistial_ad_unit_id));

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                displayJokeIntent(joke);
            }
        });

        requestNewInterstitial();

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
                spinner.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void tellJoke(View view){
        new EndpointsAsyncTask(this).execute();
    }

    private void displayJokeIntent(String joke){
        Intent displayIntent = new Intent(getActivity(),JokeDisplayActivity.class);
        displayIntent.putExtra(Intent.EXTRA_TEXT, joke);
        startActivity(displayIntent);

    }

    private void displayJokeIntent(String joke, String a){
        Intent displayIntent = new Intent(getActivity(),JokeDisplayActivity.class);
        displayIntent.putExtra(Intent.EXTRA_TEXT, joke);
        startActivity(displayIntent);

    }

    @Override
    public void onTaskCompleted(String joke) {
        this.joke = joke;

        if (mPublisherInterstitialAd.isLoaded()) {
            mPublisherInterstitialAd.show();
        } else {
            displayJokeIntent(joke);
        }

        spinner.setVisibility(View.GONE);
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }
}

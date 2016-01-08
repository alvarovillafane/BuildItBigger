package com.alvaro.jokedisplay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeDisplayFragment extends Fragment {

    public static final String ARG_JOKE = "arg joke";

    public JokeDisplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_display, container, false);

        Bundle args = this.getArguments();
        if(args != null) {
            String jokeString = args.getString(ARG_JOKE);
            TextView jokeTextView = (TextView) rootView.findViewById(R.id.joke_text_view);
            jokeTextView.setText(jokeString);
        } else {
            Toast.makeText(getActivity(), getString(R.string.no_joke_retrieved), Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    public static JokeDisplayFragment newInstance(String joke) {
        JokeDisplayFragment fragment = new JokeDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_JOKE, joke);
        fragment.setArguments(args);
        return fragment;
    }

}

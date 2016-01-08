package com.alvaro.jokedisplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class JokeDisplayActivity extends AppCompatActivity {

    private final String  FRAG_TAG = "Joke Display Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String jokeString  = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        if(jokeString != null){
            JokeDisplayFragment jokeDisplayFragment =
                    JokeDisplayFragment.newInstance(jokeString);

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
              .add(R.id.container,jokeDisplayFragment, FRAG_TAG)
              .addToBackStack(null)
              .commit();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

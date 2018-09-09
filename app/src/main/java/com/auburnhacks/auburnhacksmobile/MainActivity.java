package com.auburnhacks.auburnhacksmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.auburnhacks.auburnhacksmobile.views.FaqFragment;

public class MainActivity extends AppCompatActivity {

    private FaqFragment mFaqFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_schedule:
                    return true;
                case R.id.navigation_faq:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, mFaqFragment)
                            .commit();
                    return true;
                case R.id.navigation_sponsors:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFaqFragment = new FaqFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_register:
                String registerUrl = getString(R.string.url_registration);
                Intent registerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(registerUrl));
                startActivity(registerIntent);
                return true;
            case R.id.settings_slack:
                String slackUrl = getString(R.string.url_slack);
                Intent slackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(slackUrl));
                startActivity(slackIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

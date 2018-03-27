package com.newnews.newnews;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        Log.d("test", "In DetailActivitu");

        if (getIntent().getStringExtra("source").equals("detail")) {
            Log.d("test", "about to open fragment");
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(getIntent().getExtras());
            Log.d("test", "getIntent" + getIntent().getExtras());
            manager.beginTransaction().replace(R.id.container_detail, detailFragment).commit();
        }
        if (getIntent().getStringExtra("source").equals("bbc")) {
            BBCDetailFragment bbcDetailFragment = new BBCDetailFragment();
            bbcDetailFragment.setArguments(getIntent().getExtras());
            toolbar.setTitle("BBC");
            manager.beginTransaction().replace(R.id.container_detail, bbcDetailFragment).commit();
        }
        if (getIntent().getStringExtra("source").equals("about")) {
            AboutFragment aboutFragment = new AboutFragment();
            aboutFragment.setArguments(getIntent().getExtras());
            toolbar.setTitle("About");
            manager.beginTransaction().replace(R.id.container_detail, aboutFragment).commit();
        }

        if (getIntent().getStringExtra("source").equals("create")) {
            CreateFragment createFragment = new CreateFragment();
            createFragment.setArguments(getIntent().getExtras());
            toolbar.setTitle("Cancel");
            manager.beginTransaction().replace(R.id.container_detail, createFragment).commit();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

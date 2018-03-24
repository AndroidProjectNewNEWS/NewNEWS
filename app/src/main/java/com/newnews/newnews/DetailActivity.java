package com.newnews.newnews;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        BBCDetailFragment detailFragment = new BBCDetailFragment();
        detailFragment.setArguments(getIntent().getExtras());

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container_detail, detailFragment).commit();
    }
}

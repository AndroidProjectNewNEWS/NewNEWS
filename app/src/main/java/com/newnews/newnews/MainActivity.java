package com.newnews.newnews;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newnews.newnews.Data.Article;

public class MainActivity extends AppCompatActivity {
/*
    EditText title, author;
    Button button_upload;

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=(EditText)findViewById(R.id.title);
        author=(EditText)findViewById(R.id.author);

        button_upload=(Button)findViewById(R.id.button_upload);
        button_upload.setOnClickListener(this);

        ref= FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        if(v==button_upload){
            Article newArticle=new Article(title.getText().toString(),author.getText().toString());
            ref.push().setValue(newArticle);
        }
    }
    */

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference();

        FragmentList fragmentList = new FragmentList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.list_container, fragmentList).commit();
    }


}
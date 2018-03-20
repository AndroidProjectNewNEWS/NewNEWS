package com.newnews.newnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newnews.newnews.Data.Article;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
}
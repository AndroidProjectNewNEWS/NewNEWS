package com.newnews.newnews;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {

    TextView title, imgDes, content, time, author;
    ImageView bodyImg;

    DatabaseReference articleRef;
    String articleUID;
    Article article;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        setHasOptionsMenu(true);

        title = rootView.findViewById(R.id.title_detail);
        time = rootView.findViewById(R.id.time_detail);
        author = rootView.findViewById(R.id.author_detail);
        imgDes = rootView.findViewById(R.id.bodyImgDescription_detail);
        content = rootView.findViewById(R.id.content_detail);
        bodyImg = rootView.findViewById(R.id.bodyImg);

        articleUID = getArguments().getString("uid");
        Log.d("hehe", "uid:" + articleUID);
        articleRef = FirebaseDatabase.getInstance().getReference().child("articles").child(articleUID);
        Log.d("hehe", "ref" + articleRef);
        articleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("hehe", "datasnapshot: " + dataSnapshot.toString());
                article = dataSnapshot.getValue(Article.class);
                title.setText(article.getTitle());
                time.setText(article.getTime());
                author.setText(article.getAuthor());
                Picasso.with(getContext()).load(article.getImgUrl()).into(bodyImg);
                imgDes.setText(article.getImgDes());
                content.setText(article.getContent());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.fragment_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                if (!article.getUrl().trim().isEmpty() && article.getUrl() != null) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } else {
                    Toast.makeText(getActivity(), "This article has no sharable link", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}














































package com.newnews.newnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class FragmentList extends Fragment {

    private FirebaseRecyclerAdapter recyclerAdapter;

    public FragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragment layout
        final View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // Adapter
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("articles");

        Query query = databaseRef.orderByKey();
        FirebaseRecyclerOptions<Article> options = new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(query, Article.class)
                .build();
        recyclerAdapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ArticleViewHolder holder, int position, @NonNull final Article model) {
                holder.setTitle(model.getTitle());
                holder.setAuthor(model.getAuthor());
                holder.setImage(model.getEntryImgUrl());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), DetailActivity.class);
                        i.putExtra("source", "detail");
                        i.putExtra("uid", model.getUid());
                        Log.d("test",model.getUid());
                        startActivity(i);
                    }
                });
            }

            @Override
            public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_recycler, parent, false);
                return new ArticleViewHolder(itemView);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        return rootView;
    }

    // onStart, onStop are to start and stop listening FirebaseRecyclerAdapter
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        recyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }

    // Recycler ViewHolder
    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        View mView;

        ArticleViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        void setTitle(String title) {
            TextView title_row = itemView.findViewById(R.id.title_recycler);
            title_row.setText(title);
        }

        void setAuthor(String author) {
            TextView author_row = itemView.findViewById(R.id.author_recycler);
            author_row.setText(author);
        }

        void setImage(String imgUrl) {
            ImageView image = itemView.findViewById(R.id.image_recycler);
            if (imgUrl != null && imgUrl.length() > 0) {
                Picasso.with(mView.getContext()).load(imgUrl).into(image);
            }
        }
    }

}









































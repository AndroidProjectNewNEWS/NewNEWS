package com.newnews.newnews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.newnews.newnews.Data.Article;

public class FragmentList extends Fragment {

    RecyclerView recyclerView;

    private DatabaseReference ref;
    private FirebaseRecyclerAdapter recyclerAdapter;

    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        ref = FirebaseDatabase.getInstance().getReference().child("articles");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query=ref.orderByKey();
        FirebaseRecyclerOptions<Article> options = new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(query, Article.class)
                .build();
        recyclerAdapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ArticleViewHolder holder, int position, @NonNull Article model) {
                Toast.makeText(getContext(),model.getTitle(),Toast.LENGTH_SHORT).show();
                holder.setTitle(model.getTitle());
                holder.setAuthor(model.getAuthor());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //start new activity to show article/webview
                    }
                });
            }

            @Override
            public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
                return new ArticleViewHolder(itemView);
            }
        };
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView title_row = itemView.findViewById(R.id.title_row);
            title_row.setText(title);
        }

        public void setAuthor(String author) {
            TextView author_row = itemView.findViewById(R.id.author_row);
            author_row.setText(author);
        }
    }
}









































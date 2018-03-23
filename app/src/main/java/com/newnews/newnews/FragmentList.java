package com.newnews.newnews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.newnews.newnews.Model.Article;
import com.squareup.picasso.Picasso;

public class FragmentList extends Fragment {

    // todo: Add listener
    private RecyclerView recyclerView;
    private DatabaseReference databaseRef;
    private StorageReference storageRef;
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
        recyclerView = rootView.findViewById(R.id.recyclerView);
        databaseRef = FirebaseDatabase.getInstance().getReference().child("articles");
        storageRef = FirebaseStorage.getInstance().getReference().child("titleImages");

        Query query = databaseRef.orderByKey();
        FirebaseRecyclerOptions<Article> options = new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(query, Article.class)
                .build();
        recyclerAdapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(options) {
            Uri imgUri;

            @Override
            protected void onBindViewHolder(@NonNull ArticleViewHolder holder, int position, @NonNull Article model) {
                Toast.makeText(getContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
                holder.setTitle(model.getTitle());
                holder.setAuthor(model.getAuthor());
                storageRef.child(String.valueOf(position) + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imgUri = uri;
                    }
                });

                holder.setImage(getContext(), imgUri);

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //start new activity to show article/ webView
                    }
                });
            }

            @Override
            public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
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
            TextView title_row = itemView.findViewById(R.id.title);
            title_row.setText(title);
        }

        void setAuthor(String author) {
            TextView author_row = itemView.findViewById(R.id.author);
            author_row.setText(author);
        }

        void setImage(Context context, Uri imgUrl) {
            ImageView image = itemView.findViewById(R.id.image_entry);
            Picasso.with(context).load(imgUrl).into(image);
        }
    }
}









































package com.newnews.newnews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;
import com.newnews.newnews.Data.Article;

public class FragmentList extends Fragment {

    ListView listView;


    private DatabaseReference ref;
    private ListAdapter adapter;

    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ref = FirebaseDatabase.getInstance().getReference();

        Query query = ref.orderByKey();
        FirebaseListOptions<Article> options = new FirebaseListOptions.Builder<Article>()
                .setLayout(R.layout.list_row)
                .setQuery(query, Article.class)
                .build();

        adapter = new FirebaseListAdapter<Article>(options) {
            @Override
            protected void populateView(View v, Article model, int position) {
                Toast.makeText(getContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
                TextView title_row = v.findViewById(R.id.title_row);
                TextView author_row = v.findViewById(R.id.auther_row);

                title_row.setText(model.getTitle());
                author_row.setText(model.getAuthor());
            }
        };

        listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        return view;
    }

}

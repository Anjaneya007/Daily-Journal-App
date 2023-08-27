package com.example.firestoreown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends FirestoreRecyclerAdapter<Blog, BlogAdapter.ViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BlogAdapter(@NonNull FirestoreRecyclerOptions<Blog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Blog model) {
        holder.title.setText(model.getTitle());
        holder.body.setText(model.getBody());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterpage,parent,false);

        return new ViewHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,body;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleview);
            body=itemView.findViewById(R.id.bodyview);
        }
    }
}

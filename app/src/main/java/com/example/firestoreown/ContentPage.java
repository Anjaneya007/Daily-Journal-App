package com.example.firestoreown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContentPage extends AppCompatActivity {
    private FirebaseAuth mAuth;

    FirebaseUser user;
    Toolbar toolbar;
    RecyclerView recyclerView;

    ArrayList<Blog> blogList=new ArrayList<Blog>();

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    private final CollectionReference collectionReference = db.collection("blog");

    private BlogAdapter blogAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);

        mAuth = FirebaseAuth.getInstance();

        Query query=collectionReference.orderBy("title");



        FirestoreRecyclerOptions<Blog> options=new FirestoreRecyclerOptions.Builder<Blog>()
                .setQuery(query,Blog.class)
                .build();

        blogAdapter=new BlogAdapter(options);

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(blogAdapter);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        FirebaseUser currentUser = mAuth.getCurrentUser();

//        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if(!queryDocumentSnapshots.isEmpty()){
//                    for(QueryDocumentSnapshot blogs : queryDocumentSnapshots){
//                        Blog x=blogs.toObject(Blog.class);
//                        blogList.add(x);
//                    }
//
//                    BlogAdapter blogAdapter=new BlogAdapter(ContentPage.this,blogList);
//
//                    recyclerView.setAdapter(blogAdapter);
//                    blogAdapter.notifyDataSetChanged();
//
//
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(ContentPage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                blogAdapter.deleteItem(viewHolder.getAdapterPosition());
//                Blog z=blogList.get(viewHolder.getAdapterPosition());
//
//
//
//
//                collectionReference.document(String.valueOf(z)).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(ContentPage.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(ContentPage.this, "Failed to delete due to a error", Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        }).attachToRecyclerView(recyclerView);




    }


    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            currentUser.reload();
        }

        blogAdapter.startListening();

    }

    public void onStop() {

        super.onStop();
        blogAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//
//            case R.id.add_ic:
//
//                break;
//
//            case R.id.exit_ic:
//                if(mAuth !=null){
//                    mAuth.signOut();
//
//                    startActivity(new Intent(ContentPage.this,MainActivity.class));
//                }
//
//                break;
//        }

        if(item.getItemId()==R.id.exit_ic){
            if(mAuth !=null){
                    mAuth.signOut();

                    startActivity(new Intent(ContentPage.this,MainActivity.class));
                }

        }

        if(item.getItemId()==R.id.add_ic){
            if(mAuth !=null){

                startActivity(new Intent(ContentPage.this,AddContent.class));


            }

        }

        return super.onOptionsItemSelected(item);
    }

}
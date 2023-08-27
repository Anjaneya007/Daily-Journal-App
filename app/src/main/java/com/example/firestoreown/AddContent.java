package com.example.firestoreown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddContent extends AppCompatActivity {

    private EditText titleadd,bodyadd;
    private Button saveadd;

    Toolbar toolbar;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    private CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        titleadd=findViewById(R.id.titleAdd);
        bodyadd=findViewById(R.id.bodyAdd);
        saveadd=findViewById(R.id.saveAdd);

        saveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=titleadd.getText().toString().trim();
                String body=bodyadd.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(getApplicationContext(),
                                    "Please enter title!!",
                                    Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(body)) {
                    Toast.makeText(getApplicationContext(),
                                    "Please enter body!!",
                                    Toast.LENGTH_LONG)
                            .show();

                    return;
                }

                addToFireStore(title,body);



            }
        });


    }



    private void addToFireStore(String title, String body) {
        collectionReference=db.collection("blog");
        Blog blog=new Blog(title,body);

        collectionReference.add(blog).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AddContent.this, "Successfully added", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(AddContent.this,ContentPage.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddContent.this, "Failed to add", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
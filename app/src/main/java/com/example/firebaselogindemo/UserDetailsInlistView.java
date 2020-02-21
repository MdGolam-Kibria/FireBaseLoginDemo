package com.example.firebaselogindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsInlistView extends AppCompatActivity {
    List<ModelClass> list;
    CustomAdapter customAdapter;
    ListView listView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_inlist_view);
        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("logIndemoTest");
        list = new ArrayList<>();
        customAdapter = new CustomAdapter(UserDetailsInlistView.this, list);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ModelClass modelClass = data.getValue(ModelClass.class);
                    list.add(modelClass);
                }
                listView.setAdapter(customAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}

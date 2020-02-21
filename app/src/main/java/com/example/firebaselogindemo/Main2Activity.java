package com.example.firebaselogindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    EditText name, age;
    Button save, load;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        load = findViewById(R.id.load);
        save = findViewById(R.id.save);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("logIndemoTest");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataInListView();
            }
        });
    }

    private void loadDataInListView() {
        Intent intent = new Intent(Main2Activity.this, UserDetailsInlistView.class);
        startActivity(intent);
    }

    private void save() {
        String userName = name.getText().toString().trim();
        String userAge = name.getText().toString().trim();
        String key = databaseReference.getKey();
        ModelClass modelClass = new ModelClass(userName, userAge);
        databaseReference.child(key).setValue(modelClass);
        Toast.makeText(this, "data is added", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logoutID) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

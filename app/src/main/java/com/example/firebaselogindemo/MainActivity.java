package com.example.firebaselogindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    Button login;
    TextView resister;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        login = (Button) findViewById(R.id.btn);
        resister = (TextView) findViewById(R.id.textView);
        this.setTitle("Login");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod();
            }
        });
        resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void loginMethod() {
        String email = name.getText().toString();
        String pass = password.getText().toString();
        if (email.isEmpty()) {
            name.setError("empty email address");
            name.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            name.setError("enter a valid email address");
            name.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("empty password");
            password.requestFocus();
            return;
        }
        if (password.length() < 8) {
            password.setError("Too poor password");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "login unsuccesfull", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

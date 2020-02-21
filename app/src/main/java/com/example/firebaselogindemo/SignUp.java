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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {
    EditText name, password;
    Button resister;
    TextView loginText;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        resister = (Button) findViewById(R.id.btn);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loginText = (TextView) findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        this.setTitle("Resister");
        resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userResister();
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userResister() {
        progressBar.setVisibility(View.VISIBLE);
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
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Resister is succesfull", Toast.LENGTH_LONG).show();
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//jodi age akoi email address a resister kora thake  tohole ai condition hobe
                        Toast.makeText(SignUp.this, "user already resistered", Toast.LENGTH_LONG).show();
                        name.setError("this is usable email");
                        name.requestFocus();
                        return;
                    } else {
                        Toast.makeText(SignUp.this, "Resister is not succesfull: = "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

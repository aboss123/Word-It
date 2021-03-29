package com.ash.word_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    Button login;
    EditText email, password;
    FirebaseAuth firebaseAuth;
    TextView switch_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_btn);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        switch_login = findViewById(R.id.switch_login);
        firebaseAuth = FirebaseAuth.getInstance();

        switch_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(e)) {
                    Toast.makeText(Login.this, "Email is required",  Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(pass))
                    Toast.makeText(Login.this, "Password is required",  Toast.LENGTH_SHORT).show();

                if(pass.length() < 6)
                    Toast.makeText(Login.this, "Password length must be greater than 6",  Toast.LENGTH_SHORT).show();

                if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(pass) && pass.length() > 6)
                    firebaseAuth.signInWithEmailAndPassword(e, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Auth.login = true;
                                startActivity(new Intent(getApplicationContext(), Auth.class));
                            }
                            else
                                Toast.makeText(Login.this, "Unexpected Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });
    }
}

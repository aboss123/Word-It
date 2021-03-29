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

import org.w3c.dom.Text;

import java.util.Objects;

public class Register extends AppCompatActivity {

    Button register;
    EditText email, password, retype_password;
    FirebaseAuth firebaseAuth;
    TextView switch_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register_btn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        retype_password = findViewById(R.id.re_type_password);
        switch_register = findViewById(R.id.switch_register);

        firebaseAuth = FirebaseAuth.getInstance();

        // If user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        switch_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String retype = retype_password.getText().toString().trim();

                if (TextUtils.isEmpty(e)) {
                    Toast.makeText(Register.this, "Email is required",  Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(pass))
                    Toast.makeText(Register.this, "Password is required",  Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(retype))
                    Toast.makeText(Register.this, "Must retype password",  Toast.LENGTH_SHORT).show();

                if(pass.length() < 6)
                    Toast.makeText(Register.this, "Password length must be greater than 6",  Toast.LENGTH_SHORT).show();

                if (!retype.equals(pass))
                    Toast.makeText(Register.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();

                if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(retype) && !TextUtils.isEmpty(pass) && pass.length() > 6 && retype.equals(pass)) {
                    firebaseAuth.createUserWithEmailAndPassword(e, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                                startActivity(new Intent(getApplicationContext(), Auth.class));
                            else
                                Toast.makeText(Register.this, "Unexpected Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

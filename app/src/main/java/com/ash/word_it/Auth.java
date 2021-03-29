package com.ash.word_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Auth extends AppCompatActivity {

    public static boolean login = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        final TypeWriter writer = findViewById(R.id.writer);
        writer.setText("");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        };
        if (login)  {
            writer.animate("Logged In", 50, runnable);
            login = false;
        }
        else writer.animate("Account Created", 50, runnable);
    }
}

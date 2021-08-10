package com.example.realtimedbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void OpenSingIn(View view) {
        startActivity(new Intent(getApplicationContext(),SingIn.class));

    }

    public void OpenSingUp(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));
    }
}

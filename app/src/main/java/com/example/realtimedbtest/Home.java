package com.example.realtimedbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Home extends AppCompatActivity {

    Button BtnProfil, BtnSelles, BtnPruchse, BtnStock;
    TextView Welcome;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button BtnProfil = (Button) findViewById(R.id.ProfilBtn);
        Button BtnStock = (Button) findViewById(R.id.StockBtn);
        Button BtnSelles = (Button) findViewById(R.id.SellesBtn);
        Welcome = findViewById(R.id.welcomeT);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Welcome.setText(documentSnapshot.getString("Name"));
            }
        });


    }

    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
        finish();
    }

    public void OpenAdd(View view) {
        startActivity(new Intent(getApplicationContext(), Purchase.class));

    }

    public void OpenProfil(View view) {
        Intent intent = new Intent(this, Profil.class);
        startActivity(intent);
    }

    public void OpenStock(View view) {
        startActivity(new Intent(getApplicationContext(),Stock.class));
    }

    public void OpenHistory(View view) {
        startActivity(new Intent(getApplicationContext(),History.class));
    }


    public void OpenSell(View view) {
        startActivity(new Intent(getApplicationContext(),SellList.class));
    }

    public void OpenDelete(View view) {
        startActivity(new Intent(getApplicationContext(),DeleteProduct.class));
    }
}


package com.example.realtimedbtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button BtnSingUp, SingInPage;
    EditText mName, mPassword, mEmail, mPhone, mCPassword;
    ProgressBar progressBar;
    CheckBox mCheck;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBarColor));
        }


        Button BtnSingUp = (Button) findViewById(R.id.BtnSingUp);
        Button SingInPage = (Button) findViewById(R.id.SingInPage);
        SingInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingInPage();
            }
        });
        final EditText mName = (EditText) findViewById(R.id.mName);
        final EditText mPassword = (EditText) findViewById(R.id.mPassword);
        final EditText mEmail = (EditText) findViewById(R.id.mEmail);
        final EditText mPhone = (EditText) findViewById(R.id.mPhone);
        final EditText mCPassword = (EditText) findViewById(R.id.mCPassword);
        mCheck = findViewById(R.id.mCheck);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        BtnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String password2 = mCPassword.getText().toString().trim();
                final String name = mName.getText().toString().trim();
                final String phone = mPhone.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    mName.setError("Name is Required");
                }
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 6) {
                    mPassword.setError("Password Must be 6 Characters or More");
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    mCPassword.setError("You Need To Confirm Your Password ");
                    return;
                }
                if (!password.equals(password2)) {
                    mCPassword.setError("Password confirmation doesn't match Password");
                    return;
                }

                if (mCheck.isChecked() == false) {
                    Toast.makeText(Register.this, "You Must Accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                // DataBase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            UserID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(UserID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", name);
                            user.put("Email", email);
                            user.put("Phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Task", "User Profil Is created for" + UserID);

                                }
                            });
                            documentReference.set(user).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TASk", "FAIL" + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });


    }

    public void openSingInPage() {
        Intent intent = new Intent(this, SingIn.class);
        startActivity(intent);
    }


    public void openConditions(View view) {
        startActivity(new Intent(getApplicationContext(),Conditions.class));

    }
}

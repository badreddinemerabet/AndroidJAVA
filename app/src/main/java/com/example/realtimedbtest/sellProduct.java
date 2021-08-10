package com.example.realtimedbtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sellProduct extends AppCompatActivity {
    DatabaseReference databaseReference;
    ArrayAdapter<Product> arrayAdapter;


    private String productToDelete,productToDeleteCode;
    int positionToDelete = -1;

    private SellAdapter sellAdapter;


    FirebaseAuth fAuth;
    String UserId,NewAmount,idProduct;

    TextView name,code,price;
    EditText amount;
    int OldAmount,UpDateAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_product);

        fAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");


        name = findViewById(R.id.name1);
        code = findViewById(R.id.code1);
        amount = findViewById(R.id.Amount1);
        price = findViewById(R.id.SellPrice1);

        Intent intent = getIntent();
        String namee = intent.getStringExtra("name1");
        String codee = intent.getStringExtra("code1");
        String amountt = intent.getStringExtra("amount1");
        String pricee = intent.getStringExtra("price1");
        name.setText(namee);
        code.setText(codee);
        amount.setText(amountt);
        price.setText(pricee);
        NewAmount = amountt;
        idProduct = namee+codee+pricee+amountt;
        OldAmount = Integer.parseInt(amountt);




    }

    public void sellp(View view) {
        Query query = databaseReference.orderByChild("userId").equalTo(UserId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    String nom = name.getText().toString().trim();
                    String qte = amount.getText().toString().trim();

                    String name=ds.getValue(Product.class).getName();
                    String Code=ds.getValue(Product.class).getAmount();

                    if(name.equals(nom)&&Code.equals(qte)){
                        //String key = ds.getKey();
                        ds.getRef().removeValue();
                        sellAdapter.deleteItem(positionToDelete);
                        sellAdapter.notifyDataSetChanged();

                    }





                }
                positionToDelete = -1;
                productToDelete = null;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void UpDateProduct(View view) {
        if (isAmountChanged()){
            Toast.makeText(this, "Amount UpDated", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "test2", Toast.LENGTH_SHORT).show();
        }
            
    }

    private boolean isAmountChanged() {
       if (!NewAmount.equals(amount.getText().toString())){
           UpDateAmount = Integer.parseInt(amount.getText().toString());


           if (UpDateAmount<OldAmount) {

               databaseReference.child(idProduct).child("amount").setValue(amount.getText().toString());
               return true;
           }else {
               Toast.makeText(this, "The New Amount Can't Be Bigger Than The Older", Toast.LENGTH_SHORT).show();
               return false;
           }


       }else {

           Toast.makeText(this, "Change the Amount", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

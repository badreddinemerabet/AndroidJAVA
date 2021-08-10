package com.example.realtimedbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Purchase extends AppCompatActivity {
        EditText name, lot, code, price, sellPrice, amount;
        Button saveBtn;
        DatabaseReference Productref,Historyref;
        Product mbr;
        String UserID;
        FirebaseAuth fAuth;

    Calendar calendar = Calendar.getInstance();
    String date = DateFormat.getDateInstance().format(calendar.getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.purchasestatusbar));
        }

        name = findViewById(R.id.Name);
        lot = findViewById(R.id.Lot);
        code = findViewById(R.id.Code);
        price = findViewById(R.id.Price);
        sellPrice = findViewById(R.id.SellPrice);
        amount = findViewById(R.id.Amount);
        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        saveBtn = findViewById(R.id.Save);
        mbr = new Product();

        Productref = FirebaseDatabase.getInstance().getReference("Product");
        Historyref = FirebaseDatabase.getInstance().getReference("History");


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Nname = name.getText().toString().trim();
                String lott = lot.getText().toString().trim();
                String strprice=price.getText().toString();
                String strSellprice=sellPrice.getText().toString();
                String strCode = code.getText().toString();
                String strAmount = amount.getText().toString();


                if (TextUtils.isEmpty(Nname)){
                    name.setError("Name is Required");
                    return;
                }
                if ((lott == "0" )||(TextUtils.isEmpty(lott))){
                    lot.setError("Lot is Required And Can't Be 0");
                    return;
                }
               if (TextUtils.isEmpty(strCode)){
                    code.setError("Code is Required");
                    return;
                }
                 if (TextUtils.isEmpty(strprice)){
                    price.setError("Price is Required");
                    return;
                }
                if (TextUtils.isEmpty(strSellprice)){
                    sellPrice.setError("Sell Price is Required");
                    return;
                }
                if (TextUtils.isEmpty(strAmount)){
                    amount.setError("Amount is Required");
                    return;
                }



                String namee = name.getText().toString().trim();
                String codee = namee + strCode + strSellprice + strAmount;

                mbr.setName(name.getText().toString().trim());
                mbr.setLot(lott);
                mbr.setCode(strCode);
                mbr.setPrice(strprice);
                mbr.setSellPrice(strSellprice);
                mbr.setAmount(strAmount);
                mbr.setUserId(UserID);
                mbr.setDate(date);
                Productref.child(codee).setValue(mbr);
                Historyref.push().setValue(mbr);

                Toast.makeText(Purchase.this, "The Product "+Nname+" is Added To Your Stock", Toast.LENGTH_LONG).show();



                name.getText().clear();
                lot.getText().clear();
                code.getText().clear();
                price.getText().clear();
                sellPrice.getText().clear();
                amount.getText().clear();
            }
        });


    }
}


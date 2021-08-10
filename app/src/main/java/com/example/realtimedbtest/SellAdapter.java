package com.example.realtimedbtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SellAdapter extends BaseAdapter {
    ArrayList<Product> products;
    Context context;

    public SellAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.productsell, null);

            TextView name = convertView.findViewById(R.id.NameTVS11);
            TextView code = convertView.findViewById(R.id.CodeTVS11);
            TextView amount = convertView.findViewById(R.id.AmountTVS11);
            TextView sellprice = convertView.findViewById(R.id.SellPriceTVS11);
            TextView lot = convertView.findViewById(R.id.LotTVS11);


            name.setText(getItem(position).getName());
            code.setText(getItem(position).getCode());
            amount.setText(getItem(position).getAmount());
            sellprice.setText(getItem(position).getSellPrice());
            lot.setText(getItem(position).getLot());




        }
        return convertView;
    }

    public void deleteItem(int positionToDelete) {
        products.remove(positionToDelete);
        this.notifyDataSetChanged();
    }
}

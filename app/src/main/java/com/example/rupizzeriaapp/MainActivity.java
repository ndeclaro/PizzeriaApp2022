package com.example.rupizzeriaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import RUpizzeria.Order;
import RUpizzeria.StoreOrders;

public class MainActivity extends AppCompatActivity {

    private StoreOrders store = new StoreOrders();
    private Order startOrder = new Order();
    private StoreOrders currentStore = store;
    private Order currentOrder = startOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chicagoOpen(View view){
        Intent intent = new Intent(this, ChicagoActivity.class);
        intent.putExtra("ORDER", currentOrder);
        startActivityForResult(intent, 1);
    }

    public void nyOpen(View view){
        Intent intent = new Intent(this, NYActivity.class);
        intent.putExtra("ORDER", currentOrder);
        startActivityForResult(intent, 1);
    }

    public void orderOpen(View view){
        Intent intent = new Intent(this, orderActivity.class);
        intent.putExtra("ORDER", currentOrder);
        intent.putExtra("STORE", currentStore);
        startActivityForResult(intent, 2);
    }

    public void storeOpen(View view){
        Intent intent = new Intent(this, storeActivity.class);
        intent.putExtra("STORE", currentStore);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Order result = (Order) data.getSerializableExtra("ORDER");
                Log.i("Main", result.toString());
                currentOrder = result;
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                Order resultOrder = (Order) data.getSerializableExtra("ORDER");
                Log.i("Main", resultOrder.toString());
                StoreOrders resultStore = (StoreOrders) data.getSerializableExtra("STORE");
                Log.i("Main", resultStore.orderList());
                currentOrder = resultOrder;
                currentStore = resultStore;
            }
        }
        if(requestCode == 3){
            if(resultCode == RESULT_OK) {
                StoreOrders resultStore = (StoreOrders) data.getSerializableExtra("STORE");
                Log.i("Main", resultStore.orderList());
                currentStore = resultStore;
            }
        }
    }
}
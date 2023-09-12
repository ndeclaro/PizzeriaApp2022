package com.example.rupizzeriaapp;

/**
 * class to handle store order actions
 * @author Vinay Kumar, Noel Declaro
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import RUpizzeria.*;
import RUpizzeria.pizza.*;

public class storeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private Spinner orderSelect;
    private TextView orderDisplay;
    private TextView totalDisplay;

    private StoreOrders currentStore;
    private ArrayAdapter<Integer> adapter;
    private ArrayList<Integer> orders = new ArrayList<>();


    /**
     * method to initialize the store activity
     * @param savedInstanceState instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        this.currentStore = (StoreOrders) getIntent().
                getSerializableExtra("STORE");
        orderDisplay = findViewById(R.id.display);
        totalDisplay = findViewById(R.id.orderPrice);
        orderSelect = findViewById(R.id.spinnerOrder);
        loadOrder();
        adapter = new ArrayAdapter<Integer>(this,
                androidx.appcompat.R.layout.
                        support_simple_spinner_dropdown_item, orders);
        orderSelect.setAdapter(adapter);
        orderSelect.setOnItemSelectedListener(this);

    }

    /**
     * helper to load the orders in the storeOrders object
     */
    public void loadOrder(){
        if(!currentStore.getStoreOrders().isEmpty()){
            for(Order order: currentStore.getStoreOrders())
                orders.add(order.getOrderNumber());
        }
    }

    /**
     * method to handle selected item in the spinnder
     * @param adapterView reference for items
     * @param view instnace
     * @param i position of the item
     * @param l reference
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(!currentStore.getStoreOrders().isEmpty()){
            String selected = adapterView.getItemAtPosition(i).toString();
            int select = Integer.valueOf(selected);
            for(Order order: currentStore.getStoreOrders()){
                if(order.getOrderNumber() == select){
                    orderDisplay.setText(order.toString());
                    totalDisplay.setText(" $" +
                            String.format("%.2f", order.orderTotal()));
                    break;
                }
            }
        }
    }

    /**
     * method for validating implemenation of spinner
     * @param adapterView reference
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    //nothing
    }

    /**
     * method to handle remove order when clicking item
     * @param view reference
     */
    public void removeOrder(View view){
        if(currentStore.getStoreOrders().isEmpty())
            Toast.makeText(getApplicationContext(),
                    getString(R.string.emptyOrder), Toast.LENGTH_LONG).show();
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Do you want to remove order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    removeOrderHelper();
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.OrderRemoved), Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),
                            "NO", Toast.LENGTH_LONG);
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }

    }

    /**
     * helper method for remove order
     */
    public void removeOrderHelper(){
        String item = orderSelect.getSelectedItem().toString();
        int select = Integer.valueOf(item);
        for(Order order: currentStore.getStoreOrders()){
            if(order.getOrderNumber() == select){
                orders.remove(order);
                adapter.remove(select);
                adapter.notifyDataSetChanged();
                currentStore.remove(order);
                if(currentStore.getStoreOrders().isEmpty()) {
                    orderDisplay.setText("");
                    totalDisplay.setText("");
                }
                //send order back to main
                Intent intent = new Intent();
                intent.putExtra("STORE", currentStore);
                setResult(RESULT_OK, intent);
                break;
            }
        }



    }

}
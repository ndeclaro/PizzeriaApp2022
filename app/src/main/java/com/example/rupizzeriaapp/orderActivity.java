package com.example.rupizzeriaapp;

/**
 * class to handle order activity
 * @author Vinay Kumar, Noel Declaro
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import RUpizzeria.*;
import RUpizzeria.pizza.*;
import java.util.ArrayList;


public class orderActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    private TextView orderNum;
    private TextView subTotal;
    private TextView orderTax;
    private TextView orderTotal;
    private ListView orderContents;
    private ArrayAdapter<String> adapter;

    private StoreOrders currentStore;
    private Order currentOrder;
    private ArrayList<String> pizzaList = new ArrayList<>();

    /**
     * method to initialize order activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderNum = findViewById(R.id.CurrentOrderNumber);
        subTotal = findViewById(R.id.CurrentOrderSubtotal);
        orderTax = findViewById(R.id.CurrentOrderTax);
        orderTotal = findViewById(R.id.CurrentOrderTotal);
        orderContents = findViewById(R.id.CurrentOrderList);
        this.currentOrder = (Order) getIntent().getSerializableExtra("ORDER");
        this.currentStore = (StoreOrders) getIntent().getSerializableExtra("STORE");
        Log.i("Current Order", currentOrder.toString());
        orderNum.setText(String.valueOf(currentOrder.getOrderNumber()));
        if(!currentOrder.getPizzaList().isEmpty()){
            for (Pizza pizza : currentOrder.getPizzaList())
                pizzaList.add(pizza.toString());
            subTotal.setText(" $" + String.format("%.2f", currentOrder.getSubtotal()));
            orderTax.setText(" $" + String.format("%.2f", currentOrder.getSalesTax()));
            orderTotal.setText(" $" + String.format("%.2f", currentOrder.orderTotal()));
            adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, pizzaList);
            orderContents.setOnItemClickListener(this);
            orderContents.setAdapter(adapter);
        }
    }

    /**
     * method to handle item on click for listview
     * @param adapterView list
     * @param view reference
     * @param i position in list
     * @param l reference
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String pizzaSelected = adapterView.getAdapter().getItem(i).toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Do you want to remove pizza?");
        alert.setMessage(pizzaSelected);
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                pizzaList.remove(i);
                adapter.notifyDataSetChanged();
                removePizza(pizzaSelected);
                Toast.makeText(getApplicationContext(),
                        getString(R.string.removedPizza), Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_LONG);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * method to remove pizza from the order
     * @param pizzaSelected pizza to pass
     */
    public void removePizza(String pizzaSelected){
        //remove pizza from order
        for(Pizza pizza: currentOrder.getPizzaList()) {
            if (pizza.toString().equals(pizzaSelected)) {
                currentOrder.getPizzaList().remove(pizza);
                break;
            }
        }if(!currentOrder.getPizzaList().isEmpty()){ // reset textviews
            subTotal.setText(" $" + String.format("%.2f", currentOrder.getSubtotal()));
            orderTax.setText(" $" + String.format("%.2f", currentOrder.getSalesTax()));
            orderTotal.setText(" $" + String.format("%.2f", currentOrder.orderTotal()));
        }else{
            subTotal.setText("");
            orderTax.setText("");
            orderTotal.setText("");
        }
        //send order back to main
        Intent intent = new Intent();
        intent.putExtra("ORDER", currentOrder);
        intent.putExtra("STORE", currentStore);
        setResult(RESULT_OK, intent);
        Log.i("Current Order", currentOrder.toString());
    }

    /**
     * method to clear all pizzas from order
     * @param view reference
     */
    public void clearPizza(View view){
        if(currentOrder.getPizzaList().isEmpty())
            Toast.makeText(getApplicationContext(),
                    getString(R.string.emptyOrder), Toast.LENGTH_LONG).show();
        else{
            pizzaList.clear();
            adapter.notifyDataSetChanged();
            currentOrder.clearPizzas();
            subTotal.setText("");
            orderTax.setText("");
            orderTotal.setText("");
            Intent intent = new Intent();
            intent.putExtra("ORDER", currentOrder);
            intent.putExtra("STORE", currentStore);
            setResult(RESULT_OK, intent);
            Log.i("Current Order", currentOrder.toString());
            Toast.makeText(getApplicationContext(),
                    getString(R.string.emptyOrder), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * method to add order to storeOrders
     * @param view reference
     */
    public void placeOrder(View view){
        if(currentOrder.getPizzaList().isEmpty())
            Toast.makeText(getApplicationContext(),
                    getString(R.string.emptyOrder), Toast.LENGTH_LONG).show();
        else{
            //database update
            currentStore.add(currentOrder);
            currentOrder = new Order();
            //UI clear
            pizzaList.clear();
            adapter.notifyDataSetChanged();
            subTotal.setText("");
            orderTax.setText("");
            orderTotal.setText("");
            //send data
            Intent intent = new Intent();
            intent.putExtra("ORDER", currentOrder);
            intent.putExtra("STORE", currentStore);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
package com.example.rupizzeriaapp;

/**
 * class to create ny style pizzas
 * @author Vinay Kumar, Noel Declaro
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import RUpizzeria.*;
import RUpizzeria.pizza.*;

public class NYActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    private RadioGroup size;
    private ImageView image;
    private Spinner flavors;
    private ArrayList<topping> toppingsAvailible;
    private RecyclerView recyclerAdd;
    private recyclerAdapter.RecyclerViewClickListener listenerAdd;
    private recyclerAdapter adapterAdd;
    private ArrayList<topping> toppingsSelected;
    private RecyclerView recyclerRemove;
    private recyclerAdapter.RecyclerViewClickListener listenerRemove;
    private recyclerAdapter adapterRemove;
    private TextView price;

    private int [] itemImages = {R.drawable.pepperoni, R.drawable.sausage, R.drawable.ham,
            R.drawable.bbq_chicken, R.drawable.beef, R.drawable.onion, R.drawable.green_peppers,
            R.drawable.mushrooms, R.drawable.tomatoes, R.drawable.blackolives, R.drawable.provolone,
            R.drawable.chedder, R.drawable.parmesan};

    private boolean customizable;
    private Order currentOrder;
    private Pizza currentPizza;

    /**
     * method to initialize the activity
     * @param savedInstanceState reference
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny);
        this.currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        size = findViewById(R.id.nySize);
        image = findViewById(R.id.nyImage);
        flavors = findViewById(R.id.nyflavors);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.flavors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flavors.setAdapter(adapter);
        flavors.setOnItemSelectedListener(this);
        recyclerAdd = findViewById(R.id.nytoppingsAvailible);
        toppingsAvailible = new ArrayList<>();
        setToppingAvailible();
        setToppingAvailibleAdapter();
        recyclerRemove = findViewById(R.id.nytoppingsRemove);
        toppingsSelected = new ArrayList<>();
        setToppingSelectedAdapter();
        price = findViewById(R.id.nyPrice);
    }

    /**
     * method to recycler view adapter
     */
    private void setToppingAvailibleAdapter() {
        setToppingAvailibleOnClickListener();
        adapterAdd = new recyclerAdapter(toppingsAvailible, listenerAdd);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerAdd.setLayoutManager(layoutManager);
        recyclerAdd.setItemAnimator(new DefaultItemAnimator());
        recyclerAdd.setAdapter(adapterAdd);
    }

    /**
     * method to recycler view adapter
     */
    private void setToppingSelectedAdapter(){
        setToppingSelectedOnClickListener();
        adapterRemove = new recyclerAdapter(toppingsSelected, listenerRemove);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerRemove.setLayoutManager(layoutManager);
        recyclerRemove.setItemAnimator(new DefaultItemAnimator());
        recyclerRemove.setAdapter(adapterRemove);
    }
    /**
     * method to set on click handler for recycler view
     */
    private void setToppingAvailibleOnClickListener() {
        listenerAdd = new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(toppingsSelected.size() >= 7)
                    showAlert(getString(R.string.AlertMaxToppings));
                else if(customizable){
                    String selected = toppingsAvailible.get(position).getTopping();
                    Integer image = toppingsAvailible.get(position).getImage();
                    toppingsAvailible.remove(position);
                    adapterAdd.notifyItemRemoved(position);
                    toppingsSelected.add(new topping(selected, image));
                    adapterRemove.notifyDataSetChanged();
                    //adding topping to pizza object
                    Topping toAdd = Topping.valueOf(selected.toUpperCase().
                            replace(" ","_"));
                    currentPizza.add(toAdd);
                    price.setText(" $" + String.format("%.2f", currentPizza.price()));
                }
                else
                    showAlert(getString(R.string.AlertCannotCustomize));
            }
        };
    }
    /**
     * method to set on click handler for recycler view
     */
    private void setToppingSelectedOnClickListener(){
        listenerRemove = new recyclerAdapter.RecyclerViewClickListener(){
            @Override
            public void onClick(View v, int position) {
                String selected = toppingsSelected.get(position).getTopping();
                Integer image = toppingsSelected.get(position).getImage();
                toppingsSelected.remove(position);
                adapterRemove.notifyItemRemoved(position);
                toppingsAvailible.add(new topping(selected, image));
                adapterAdd.notifyDataSetChanged();
                //removing topping from pizza object
                Topping toRemove = Topping.valueOf(selected.toUpperCase().
                        replace(" ","_"));
                currentPizza.remove(toRemove);
                price.setText(" $" + String.format("%.2f", currentPizza.price()));
            }
        };
    }

    /**
     * method to set topping items
     */
    private void setToppingAvailible() {
        String [] toppingList = getResources().getStringArray(R.array.toppings);
        for(int i = 0; i < toppingList.length; i++)
            toppingsAvailible.add(new topping(toppingList[i], itemImages[i]));
    }

    /**
     * method for size spinnder
     * @param adapterView list
     * @param view reference
     * @param i position
     * @param l reference
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();
        PizzaFactory pizzaFactory = new NYPizza();
        if(selected.equals("Deluxe")) {
            image.setImageResource(R.drawable.nydeluxe);
            currentPizza = pizzaFactory.createDeluxe();
            customizable = false;
            onItemSelectedClear();
        }
        if(selected.equals("BBQ Chicken")) {
            image.setImageResource(R.drawable.nybbq);
            currentPizza = pizzaFactory.createBBQChicken();
            customizable = false;
            onItemSelectedClear();
        }
        if(selected.equals("Meatzza")) {
            image.setImageResource(R.drawable.nymeatzza);
            currentPizza = pizzaFactory.createMeatzza();
            customizable = false;
            onItemSelectedClear();
        }
        if(selected.equals("Build Your Own")) {
            image.setImageResource(R.drawable.nybuild);
            currentPizza = pizzaFactory.createBuildYourOwn();
            customizable = true;
            onItemSelectedClear();
        }
    }

    /**
     * method to validate spinner implementation
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //leave blank
    }

    /**
     * method to handle item selection
     */
    public void onItemSelectedClear(){
        size.clearCheck();
        price.setText("");
        if(toppingsSelected.size() > 0){
            toppingsSelected.clear();
            adapterRemove.notifyDataSetChanged();
        }
    }

    /**
     * method to handle radio button group
     * @param view reference
     */
    public void sizeOnClicked(View view){
        int sizeID = size.getCheckedRadioButtonId();
        RadioButton sizeSelect = findViewById(sizeID);
        if(sizeSelect.getText().equals("Small")){
            currentPizza.setSize(Size.SMALL);
            price.setText(" $" + String.format("%.2f", currentPizza.price()));
        } else if(sizeSelect.getText().equals("Medium")){
            currentPizza.setSize(Size.MEDIUM);
            price.setText(" $" + String.format("%.2f", currentPizza.price()));
        } else if(sizeSelect.getText().equals("Large")){
            currentPizza.setSize(Size.LARGE);
            price.setText(" $" + String.format("%.2f", currentPizza.price()));
        }
    }

    /**
     * method to handle adding pizza
     * @param view
     */
    public void placeOrder(View view){
        int sizeID = size.getCheckedRadioButtonId();
        if(sizeID == -1)
            showAlert(getString(R.string.AlertSelectSize));
        else {
            currentOrder.add(currentPizza);
            onItemSelectedClear();
            Intent intent = new Intent();
            intent.putExtra("ORDER", currentOrder);
            setResult(RESULT_OK, intent);
            Log.i("NY", currentOrder.toString());
            //finish();
            Toast.makeText(NYActivity.this,
                    getString(R.string.ToastPizzaAdded), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * method that creates alerts and displays
     * @param line string for alert
     */
    private void showAlert(String line){
        AlertDialog.Builder alert = new AlertDialog.Builder(NYActivity.this);
        alert.setTitle(line);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}

package RUpizzeria.pizza;

/**
 * The pizza class specifies the general characteristics of a pizza
 * @author Vinay Kumar, Noel Declaro
 */

import android.os.Parcelable;

import RUpizzeria.Customizable;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Pizza implements Customizable, Serializable {
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private Crust crust;
    private Size size;

    /**
     * constructor that creates a base pizza
     * @param crust object type
     */
    public Pizza(Crust crust) {
        this.crust = crust;
    }

    /**
     * price method to inherit, returns the price of the pizza
     * @return price of pizza
     */
    public abstract double price();

    /**
     * method to override that prints the pizza description
     * @return string that contains all the details of the pizza
     */
    @Override
    public abstract String toString();

    /**
     * method to override that compares pizza objects
     * @param obj object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * method that returns the crust of the pizza
     *
     * @return string that contains the crust
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * method that returns size of the pizza
     *
     * @return string that contains the size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * method that returns the list of topping on the pizza
     *
     * @return reference of toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * method that sets the size of the pizza
     *
     * @param size type to set to the pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * method to add toppings to the pizza
     *
     * @param obj topping to add
     * @return true if the topping was added false otherwise
     */
    public boolean add(Object obj) {
        if (obj instanceof Topping) {
            toppings.add((Topping) obj);
            return true;
        }
        return false;
    }

    /**
     * method to remove toppings to the pizza
     *
     * @param obj topping to remove
     * @return true if topping was removed, false otherwise
     */
    public boolean remove(Object obj) {
        if (obj instanceof Topping) {
            toppings.remove(obj);
            return true;
        }
        return false;
    }


}
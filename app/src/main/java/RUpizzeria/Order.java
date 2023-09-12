package RUpizzeria;

/**
 * The order class manages the pizza in an order
 * @author Vinay Kumar, Noel Declaro
 */

import java.io.Serializable;
import java.util.ArrayList;
import RUpizzeria.pizza.*;


public class Order implements Customizable, Serializable {
    private ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
    private static int orderNumber = 1;
    private int currentOrderNumber;

    /**
     * constructor that creates new order
     */
    public Order() {
        this.currentOrderNumber = getNewOrderNumber();
    }

    /**
     * method that creates new order number
     *
     * @return
     */
    private static int getNewOrderNumber() {
        return orderNumber++;
    }

    /**
     * method that gets the order number
     *
     * @return integer of the order number
     */
    public int getOrderNumber() {
        return currentOrderNumber;
    }


    public ArrayList<Pizza> getPizzaList() {
        return pizzaList;
    }

    /**
     * method that clears the pizzas in the order
     */
    public void clearPizzas() {
        pizzaList.clear();
    }

    /**
     * method that returns the subtotal of the order
     *
     * @return double that contains the subtotal
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzaList)
            subtotal += pizza.price();
        return subtotal;
    }

    /**
     * method that returns the sales tax of the order
     *
     * @return double that contains the sales tax
     */
    public double getSalesTax() {
        return getSubtotal() * .06625;
    }

    /**
     * method the returns the order total
     *
     * @return double that contains the order total
     */
    public double orderTotal() {
        return getSubtotal() + getSalesTax();
    }

    /**
     * method that outputs the order description
     *
     * @return string that contains the whole order
     */
    @Override
    public String toString() {
        String str = "Order Number: " + getOrderNumber() + " Order Total: $"
                + String.format("%.2f", orderTotal()) + "\n";
        for (Pizza pizza : pizzaList)
            str += pizza.toString() + "\n";
        return str;
    }

    /**
     * method that adds an pizza object to the order list
     *
     * @param obj pizza to add
     * @return true if the pizza was added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            pizzaList.add((Pizza) obj);
            return true;
        }
        return false;
    }

    /**
     * method that removes a pizza object from the order list
     *
     * @param obj pizza to remove
     * @return true if the pizza was removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            pizzaList.remove((Pizza) obj);
            return true;
        }
        return false;
    }


}

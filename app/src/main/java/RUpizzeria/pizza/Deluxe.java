package RUpizzeria.pizza;

/**
 The Deluxe class is child of the pizza class that specifies what a Deluxe pizza is
 @author Vinay Kumar, Noel Declaro
 */

import java.util.ArrayList;
public class Deluxe extends Pizza {
    private ArrayList<Topping> toppings = new ArrayList<Topping>();

    /**
     * constructor that creates base Deluxe Pizza
     * @param crust object to set
     */
    public Deluxe(Crust crust){
        super(crust);
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.ONION);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.MUSHROOM);
    }

    /**
     * price method to inherit, returns the price of the pizza
     * @return the price of the pizza
     */
    @Override
    public double price(){
        double price = 0;
        if(getSize() == Size.SMALL){
            price = 14.99;
        }
        else if(getSize() == Size.MEDIUM){
            price = 16.99;
        }
        else if(getSize() == Size.LARGE){
            price = 18.99;
        }
        return price;
    }

    /**
     * method that returns the style of the pizza
     * @return string that contains the style
     */
    public String getStyle(){
        if(getCrust().getCrustType().equals("Deep Dish"))
            return "Chicago Style";
        else
            return "New York Style";
    }

    /**
     * method to override that prints the pizza description
     * @return string that contains all the details of the pizza
     */
    @Override
    public String toString(){
        String str = "Deluxe Pizza: " + getSize() + " ("+ getStyle() +
                " - " + getCrust() + ") with ";
        for(int i = 0; i < toppings.size(); i++){
            if(i == toppings.size() - 1){
                str += toppings.get(i).getTopping();
            }
            else{
                str += toppings.get(i).getTopping() + ", ";
            }
        }
        String subtotal = " $" + String.format("%.2f", price());
        return str + subtotal;
    }

    /**
     * method to override that compares pizza objects
     * @param obj pizza object to compare
     * @return true if the object is the same, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Deluxe){
            Deluxe other = (Deluxe) obj;
            if(this.getCrust() == other.getCrust() &&
                    this.getSize() == other.getSize()
                    && this.toppings.equals(other.toppings)){
                return true;
            }
        }
        return false;
    }
}

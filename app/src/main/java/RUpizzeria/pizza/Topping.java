package RUpizzeria.pizza;

/**
 This enum class is used to specify the possible toppings for a pizza
 @author Vinay Kumar, Noel Declaro
 */

public enum Topping{
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    MUSHROOM("Mushroom"),
    ONION("Onion"),
    GREEN_PEPPER("Green Pepper"),
    BEEF("Beef"),
    HAM("Ham"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    BBQ_CHICKEN("BBQ Chicken"),
    //additional
    TOMATOES("Tomatoes"),
    PARMESAN("Parmesan"),
    BLACK_OLIVES("Black Olives");

    private final String topping;

    Topping(String topping){
        this.topping = topping;
    }
     /**
     This method returns the topping of the pizza.
     @return the topping of the pizza
     */
    public String getTopping(){
        return topping;
    }
}
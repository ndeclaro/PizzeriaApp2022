package RUpizzeria.pizza;

/**
 The "Build your own" class is child of the pizza class that specifies what a "Build your own" Pizza is
 @author Vinay Kumar, Noel Declaro
 */

public class BuildYourOwn extends Pizza {

    /**
     * constructor that creates base BuildYourOwn Pizza
     * @param crust object to set
     */
    public BuildYourOwn(Crust crust){
        super(crust);
    }

    /**
     * price method to inherit, returns the price of the pizza
     * @return the price of the pizza
     */
    @Override
    public double price(){
        double price = 0;
        int numToppings = getToppings().size();
        if(getSize() == Size.SMALL){
            price = 8.99;
        }
        else if(getSize() == Size.MEDIUM){
            price = 10.99;
        }
        else if(getSize() == Size.LARGE){
            price = 12.99;
        }

        return price + (numToppings * 1.59);
    }

    /**
     * method that returns the style of the pizza
     * @return string that contains the style
     */
    public String getStyle(){
        if(getCrust().getCrustType().equals("Pan"))
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
        String str = "Build Your Own Pizza: " + getSize() + " ("+ getStyle()
                + " - " + getCrust() + ") with ";
        for(int i = 0; i < getToppings().size(); i++){
            if(i == getToppings().size() - 1){
                str += getToppings().get(i).getTopping();
            }
            else{
                str += getToppings().get(i).getTopping() + ", ";
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
        if(obj instanceof BuildYourOwn){
            BuildYourOwn other = (BuildYourOwn) obj;
            if(this.getCrust() == other.getCrust() &&
                    this.getSize() == other.getSize()
                    && this.getToppings().equals(other.getToppings())){
                return true;
            }
        }
        return false;
    }

    /**
     * method to add toppings to the pizza
     *
     * @param obj topping to add
     * @return true if the topping was added false otherwise
     */
    @Override
    public boolean add(Object obj){
        if(obj instanceof Topping){
            Topping topping = (Topping) obj;
            if(getToppings().size() <= 7){
                getToppings().add(topping);
                return true;
            }
        }
        return false;
    }

    /**
     * method to remove toppings to the pizza
     *
     * @param obj topping to remove
     * @return true if the topping was removed false otherwise
     */
    @Override
    public boolean remove(Object obj){
        if(obj instanceof Topping){
            Topping topping = (Topping) obj;
            if(getToppings().contains(topping)){
                getToppings().remove(topping);
                return true;
            }
        }
        return false;
    }
    
}

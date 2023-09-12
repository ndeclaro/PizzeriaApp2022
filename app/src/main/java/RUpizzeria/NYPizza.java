package RUpizzeria;


/**
 * class to create NY style pizzas
 * @author Vinay Kumar, Noel Declaro
 */

import RUpizzeria.pizza.*;

public class NYPizza implements PizzaFactory {

    /**
     * creates a base Deluxe NY pizza
     * @return pizza object
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * creates a base Meatzza NY pizza
     * @return pizza object
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HANDTOSSED);
    }

    /**
     * creates a base BBQChicken NY pizza
     * @return pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * creates a base BuildYourOwn NY pizza
     * @return pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HANDTOSSED);
    }
}

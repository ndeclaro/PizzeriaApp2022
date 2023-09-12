package RUpizzeria;

import RUpizzeria.pizza.*;

/**
 * class to create Chicago style pizzas
 * @author Vinay Kumar, Noel Declaro
 */

public class ChicagoPizza implements PizzaFactory {

    /**
     * creates a base Deluxe Chicago pizza
     * @return pizza object
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEPDISH);
    }

    /**
     * creates a base Meatzza Chicago pizza
     * @return pizza object
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * creates a base BBQChicken Chicago pizza
     * @return pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * creates a base BuildYourOwn Chicago pizza
     * @return pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}

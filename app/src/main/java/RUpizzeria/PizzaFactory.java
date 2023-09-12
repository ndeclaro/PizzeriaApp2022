package RUpizzeria;

/**
 * interface for creating pizzas
 * @author Vinay Kumar, Noel Declaro
 */

import RUpizzeria.pizza.*;

public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}

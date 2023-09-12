package RUpizzeria;

import java.io.Serializable;
import java.util.ArrayList;

/**
 This StoreOrders class is used to manage the orders of the store
 @author Vinay Kumar, Noel Declaro
 */

public class StoreOrders implements Customizable, Serializable {

    private ArrayList<Order> storeOrders = new ArrayList<Order>();

    /**
     * constructor used to initialize store
     */
    public StoreOrders(){
    }

    /**
     * method used to return string for export
     * @return string that contains all the store orders
     */
    public String orderList(){
        String orderList = "--Store Orders--\n";
        for(Order order: storeOrders)
            orderList += order.toString() + "\n";
        orderList += "--end of list==";
        return orderList;
    }

    public ArrayList<Order> getStoreOrders(){
        return this.storeOrders;
    }

    /**
     * method that adds orders
     * @param obj order object to compare
     * @return true if the input was added false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order) {
            storeOrders.add((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * method that removes orders
     * @param obj order object to compare
     * @return true if the input was removed false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            storeOrders.remove((Order) obj);
            return true;
        }
        return false;
    }
}

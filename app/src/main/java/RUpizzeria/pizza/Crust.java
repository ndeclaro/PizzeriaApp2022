package RUpizzeria.pizza;

/**
This enum class is used to specify the the crust types of the pizza
@author Vinay Kumar, Noel Declaro
*/

public enum Crust{
    DEEPDISH("Deep Dish"),
    THIN("Thin"),
    PAN("Pan"), 
    BROOKLYN("Brooklyn"),
    STUFFED("Stuffed"),
    HANDTOSSED("Hand Tossed");

    private final String crustType;

    Crust(String crustType){
        this.crustType = crustType;
    }
     /**
      * This method returns the crust type.
        * @return the crust type
        */
    public String getCrustType(){
        return crustType;
    }
 

  
        
   

}





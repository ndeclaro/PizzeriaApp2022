package RUpizzeria.pizza;

public enum Size {
    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");
    private final String size;

    Size(String size){
        this.size = size;
    }
     /**
      * This method returns the size of the pizza.
        * @return the size of the pizza
        */
    public String getSize(){
        return size;
    }
    
}

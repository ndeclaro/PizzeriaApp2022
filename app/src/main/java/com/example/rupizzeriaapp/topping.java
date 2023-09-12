package com.example.rupizzeriaapp;

/**
 * class for the topping object to insert into the recycler view
 * @author Vinay Kumar, Noel Declaro
 */

public class topping {
    private String topping;
    private int image;

    /**
     * constructor to initialize item for recycler view
     * @param topping string that contains the topping
     * @param image code for image in resources
     */
    public topping(String topping, int image){
        this.topping = topping;
        this.image = image;
    }

    /**
     * getter to get topping
     * @return returns string topping
     */
    public String getTopping(){
        return topping;
    }

    /**
     * method to get image code
     * @return code for image in res
     */
    public int getImage(){
        return image;
    }

    /**
     * method used to set image code
     * @param image input to set
     */
    public void setImage(int image){
        this.image = image;
    }

    /**
     * method to set topping
     * @param topping string to set topping
     */
    public void setTopping(String topping){
        this.topping = topping;
    }
}

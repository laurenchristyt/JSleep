package com.LaurenChristyJSleepRJ;

import java.util.ArrayList;

/**
 * The `Validate` class provides a method for filtering an array of `Price` objects based on a specified value and condition.
 *
 * @author Lauren Christy Tanudjaja
 * @version 1.0
 */
public class Validate {
    public Validate() {

    }
    public static ArrayList filter (Price[] list, int value, boolean less){
        ArrayList filteredPrice = new ArrayList();
        if(less == true) {
            for (Price iterator : list) {
                if(iterator.price <= value){
                    filteredPrice.add(iterator.price);
                }
            }
        }
        else{
            for (Price iterator : list) {
                if(iterator.price > value){
                    filteredPrice.add(iterator.price);
                }
            }
        }
        return filteredPrice;
    }
}
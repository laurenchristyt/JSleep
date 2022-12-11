package com.LaurenChristyJSleepRJ;

/**
 * This class represents a price for a room.
 *
 * <p>The price can have a discount applied to it, and a rebate can be provided as well.</p>
 *
 * @author Lauren Christy Tanudjaja
 */
public class Price {
    /**
     * The base price of the room.
     */
    public double price;

    /**
     * The discount applied to the price as a percentage (e.g. 20 for 20%).
     */
    public double discount;

    /**
     * Constructs a new Price instance with the given base price and no discount.
     *
     * @param price The base price of the room
     */
    public Price(double price) {
        this.price = price;
        this.discount = 0;
    }

    /**
     * Constructs a new Price instance with the given base price and discount.
     *
     * @param price    The base price of the room
     * @param discount The discount that will be applied to the original price
     */
    public Price(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    /**
     * This method is used to show the price and discount of a room.
     *
     * @return String of price and discount
     */
    public String toString() {
        return "\nPrice: " + price + "\nDiscount: " + discount;   /*
    public Price (double price, double rebate) {
         this.price = price;
         this.rebate = rebate;
         this.discount = 0;
    }
    private double getDiscountedPrice(){
        if (this.discount >= 100){
            discount = 100;
        }else if (this.discount == 100){
            return 0.0;
        }
        return this.price - (this.price * (this.discount / 100));
    }
    private double getRebatedPrice(){
        if (this.rebate >= price){
            rebate = price;
        }
        return this.price - this.rebate;
    }
    */
    }
}



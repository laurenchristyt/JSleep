package com.LaurenChristyJSleepRJ;

public class Price{
    public double price;
    //public double rebate;
    public double discount;
    //public int discount;

    public Price (double price){
        this.price = price;
        this.discount = 0;
        //    this.rebate = 0;
    }
    public Price (double price, double discount){
        this.price = price;
        this.discount = discount;
        //     this.rebate = 0;
    }
    public String toString(){
        return "Price: " + this.price + "\n" + "Discount: " + this.discount + "\n";
    }
    /*
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



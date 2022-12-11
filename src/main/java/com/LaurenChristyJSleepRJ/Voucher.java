package com.LaurenChristyJSleepRJ;

import com.LaurenChristyJSleepRJ.dbjson.Serializable;

/**
 * The `Voucher` class represents a voucher that can be applied to reduce the price of a room.
 *
 * @author  Lauren Christy Tanudjaja
 * @version 1.0
 * @see Serializable
 */
public class Voucher extends Serializable
{
    /**
     * The type of voucher (rebate or discount).
     */
    public Type type;

    /**
     * The amount of the voucher.
     */
    public double cut;

    /**
     * The name of the voucher.
     */
    public String name;

    /**
     * The unique code of the voucher.
     */
    public int code;

    /**
     * The minimum price required for the voucher to be applied.
     */
    public double minimum;

    /**
     * A boolean value indicating whether the voucher has been used.
     */
    private boolean used;
    public Voucher(String name, int code, Type type, boolean used, double minimum, double cut){
        this.name = name;
        this.code = code;
        this.type = type;
        this.used = used;
        this.minimum = minimum;
        this.cut = cut;
    }
    public boolean canApply(Price price){
        if((price.price >= this.minimum) && (this.used == false)){
            return true;
        } else {
            return false;
        }
    }
    public double apply(Price price){
        this.used = true;
        if(this.type == type.DISCOUNT){
            if(this.cut > 100){
                return 0.0;
            } else if (this.cut == 100){
                return 0.0;
            }
            return price.price - (price.price * (this.cut/100.0));
        } else {
            if(this.cut > price.price){
                this.cut = price.price;
            }
            return price.price - this.cut;
        }
    }
    public boolean isUsed (){
        return this.used = used;
    }



}

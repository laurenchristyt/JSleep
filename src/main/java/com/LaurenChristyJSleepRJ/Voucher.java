package com.LaurenChristyJSleepRJ;

import com.LaurenChristyJSleepRJ.dbjson.Serializable;
public class Voucher extends Serializable
{
    public Type type;
    public double cut;
    public String name;
    public int code;
    public double minimum;
    private boolean used;
    
    public Voucher(int id, String name, int code, Type type, boolean used, double minimum, double cut){
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
                this.cut = 100.0;
            } else if (this.cut == 100){
                return 0;
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

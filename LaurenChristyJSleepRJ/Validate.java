package LaurenChristyJSleepRJ;

import java.util.ArrayList;

public class Validate
{
    public static ArrayList filter (Price[] list, int value, boolean less){
        ArrayList<Price> returned = new ArrayList<>();
        if(less){
            for (Price i : list){
                if (i.price <= value){
                    returned.add(i);
                }
            }
        }
        else{
            for (Price i : list){
                if (i.price > value){
                    returned.add(i);
                }
            }
        }
    return returned;
    }
}

package LaurenChristyJSleepRJ;
    
import java.util.ArrayList;

public class Validate
{
    public static ArrayList filter (Price[] list, int value, boolean less){
        ArrayList<Double> returned = new ArrayList<Double>();
        if(less == true){
            for (Price price : list){
                if (price.price <= value){
                    returned.add(price.price);
                }
            }
        }
        else{
            for (Price price : list){
                if (price.price > value){
                    returned.add(price.price);
                }
            }
        }
        return returned;
    }
}

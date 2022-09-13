package LaurenChristyJSleepRJ;

public class JSleep
{
    public static void main(String[] args){
    }
    public static int getHotelId(){
        return 0;
    }
    public String getHotelName(){
        return "hotel";
    }
    public boolean isDiscount() {
        return true;
    }
    public float getDiscountedPercentage(int beforeDiscount, int afterDiscount){
        if (afterDiscount <beforeDiscount)
            return 0;
        return 0;
    }
    public int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100)
            return 0;
        return 0;
    }
    public float getOriginalPrice(int discountedPrice, float discountPercentage) {
        return (discountedPrice * (100 * ((1/100)-discountPercentage)));
    }
    public float getAdminFeePercentage(){
        return (5/100);
    }
    public float getAdminFee(int price){
        return price * getAdminFeePercentage();
    }
    public float getTotalPrice(int price, int numberOfNight) {
        int total = price * numberOfNight;
        return (total + (total * getAdminFeePercentage()));
    }
}

package LaurenChristyJSleepRJ;

public class JSleep {
    public static void main(String[] args) {
        /*Room test = createRoom();
        System.out.println(test.name);
        System.out.println(test.size);
        System.out.println(test.price.price);
        System.out.println(test.facility); 
        Payment testRoom = new Payment(1, 1, 1, "", 1, "", ""); 
        Invoice testInvoice = new Invoice(2,2,2, ""); 
        System.out.println(testRoom.print()); 
        System.out.println(testInvoice.print());
        */
        Complaint testComplain = new Complaint(1, "23 August 2022", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Room testRoom = new Room(1, "Presidential Suite", 5, testPrice, Facility.FitnessCenter,
        City.DEPOK, "JL. Margonda Raya");
        Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();
        System.out.println(testComplain.toString());
        System.out.println(testRoom.toString());
        System.out.println(testAccount.toString());
        System.out.println(testPrice.toString());
        System.out.println(testRating.toString());
    }
    
    /*
    public static int getHotelId() {
        return 0;
    }
    public static String getHotelName() {
        return "hotel";    
    }
    public static boolean isDiscount(){
        return true;
    }
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        if (beforeDiscount < afterDiscount) {
            return 0;
        }
        else if (((float) beforeDiscount - (float) afterDiscount) == 0) {
            return 0;
        }
        return (((float) beforeDiscount - (float) afterDiscount) / (float) beforeDiscount) * (float) 100.0; 
    }
    public static int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage >= 100.0) {
            return 0;
        }
        return (int) ((float) price * ((float) 100.0 - discountPercentage) / 100.0);
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage) {
        return (int) ((float) discountedPrice * (100.0 / (100.0 - discountPercentage)));
    }
    public static float getAdminFeePercentage() {
        return (float) 0.05;
    }
    public static int getAdminFee(int price) {
        return (int) ((float) price * getAdminFeePercentage());
    }
    public static int getTotalPrice(int price,int numberOfNight) {
        return (numberOfNight * (price + getAdminFee(price)));
    } 
    public static Room createRoom(){
        Price price = new Price (100000, 5);
        Room room = new Room ("hotel", 30, price, Facility.Bathtub);
        return room;
    } */
}
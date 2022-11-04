package LaurenChristyJSleepRJ;

import java.io.BufferedReader;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.List;
import com.google.gson.*;
import java.util.ArrayList;


public class JSleep {
	class Country{
		public String name;
		public int population;
		public List<String> listOfStates;
	}
    public static void main(String[] args) {
    	
     //   Account testRegex = new Account("Netlab_", "lauren.christy@gmail.com", "AkuLelah100");
       // System.out.println(testRegex.Validate());
        
       // Account testRegexFail = new Account("NetlabAja", "lauren.christy@ui.ac.id", "AkuLelah101");
        //System.out.println(testRegexFail.Validate());
        
    	try
        {
            String filepath = "json/randomRoomList.json";

            JsonTable<Account> tableAcc = new JsonTable<Account>(Account.class, filepath);
            tableAcc.add(new Account("name","email","pass"));
            tableAcc.writeJson();
            
        }
        catch (Throwable t)
        {
            t. printStackTrace() ;
        }
    	
    	/*String filepath= "C:\\Users\\laure\\Downloads\\Resource TP Modul 6-20221030\\city.json";
    	Gson gson = new Gson();
    	try {
    		BufferedReader br = new BufferedReader(new FileReader(filepath));
    		Country input = gson.fromJson(br, Country.class);
    		System.out.println("name: " + input.name);
    		System.out.println("population: " + input.population);
    		System.out.println("states: ");
    		input.listOfStates.forEach(state -> System.out.println(state));
    		
    	}
    	
    	catch (IOException e) {
    		e.printStackTrace();
    	}
        Room test = createRoom();
        System.out.println(test.name);
        System.out.println(test.size);
        System.out.println(test.price.price);
        System.out.println(test.facility); 
        Payment testRoom = new Payment(1, 1, 1, "", 1, "", ""); 
        Invoice testInvoice = new Invoice(2,2,2, ""); 
        System.out.println(testRoom.print()); 
        System.out.println(testInvoice.print());
        
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
        
        Payment testPayment = new Payment (2, 2, 2, 2);
        System.out.println(testPayment.getTime());
        System.out.println(testPayment.getDuration());
        Price[] unfilteredArray = new Price[5];
        for(int i = 0; i < unfilteredArray.length;i++){
            int j = 5000;
            unfilteredArray[i] = new Price((i+1)*j);            
        }
        System.out.println("Price List");
        for (int i = 0; i < unfilteredArray.length;i++){
            System.out.println(unfilteredArray[i].price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000, true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000, false));
        
        Room RoomA = JSleep.createRoom();
        Room RoomB = JSleep.createRoom();
        System.out.println("Membuat booking dari tanggal 15 hingga 18");
        Date start = Date.valueOf("2022-8-15");
        Date end = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start, end, RoomA));
        System.out.println("Membuat booking dari tanggal 15 hingga 18");
        Date start2 = Date.valueOf("2022-8-18");
        Date end2 = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start2, end2, RoomA));
        System.out.println("Membuat booking dari tanggal 15 hingga 18 untuk kamar berbeda");
        Date start3 = Date.valueOf("2022-8-18");
        Date end3 = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start3, end3, RoomB));
        /*Error Handling*//*
        System.out.println("\n");
        System.out.println("Membuat booking dari tanggal 20 hingga 15");
        Date start4 = Date.valueOf("2022-8-20");
        Date end4 = Date.valueOf("2022-8-15");
        System.out.println(Payment.makeBooking(start4, end4,RoomA));
        
        ArrayList<Room> RoomSerialized = new ArrayList<Room>();
         for (int i=0; i<5; i++) {
             RoomSerialized.add(i, JSleep.createRoom());
             System.out.println(RoomSerialized.get(i).toString());
         }*/
    	
        for(int i = 0; i <= 9;i++) {
            ThreadingObject thread = new ThreadingObject ("Thread " +i);
        }
    }
    
    public static List<Room> filterByCity(List <Room> list , String search, int page, int pageSize){
        return Algorithm.paginate(list, page, pageSize, c -> c.city.toString().toLowerCase().contains(search.toLowerCase()));
    }

    public static List<Room> filterByPrice(List<Room> priceList, double minPrice, double maxPrice){
        if(maxPrice == 0){
            return Algorithm.<Room>collect(priceList, p -> (p.price.price >= minPrice));
        }
        return Algorithm.<Room>collect(priceList, p -> (p.price.price <= maxPrice) && (p.price.price >= minPrice));
    }

    public static List<Room>  filterByAccountId(List <Room> list , int accountId, int page, int pageSize){
        ArrayList<Room> idid = new ArrayList<>();
        return Algorithm.paginate(list, page, pageSize, i -> i.accountId == accountId);
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
    */
    public static Room createRoom(){
        Price price = new Price (100000.0, 5);
        Room room = new Room(1, "Restaurant",30,price,Facility.AC,City.JAKARTA,"Jl.Medan");
        return room;
    } 
}
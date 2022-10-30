package LaurenChristyJSleepRJ;

public class Renter extends Serializable
{
    public int phoneNumber;
    public String address;
    public String username;
    
    public Renter(int id, String username){
        this.username = username;
        this.address = "";
        this.phoneNumber = 0;
    }
    public Renter(int id,String username, String address){
        this.username = username;
        this.address = address;
        this.phoneNumber = 0;
    }
    public Renter(int id,String username, int phoneNumber){
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    public Renter(int id, String username, int phoneNumber, String address){
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}


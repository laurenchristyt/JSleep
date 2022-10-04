package LaurenChristyJSleepRJ;

public class Room extends Serializable implements FileParser
{
    public int size;
    public String name;
    public Facility facility;
    public Price price;
    public City city;
    public String address;
    public BedType bedType;
    
    public Room (int id, String name, int size, Price price, Facility facility, City city, String address){
        super(id);
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
    } 
    public String toString(){
        return "ID: " + id + "\n" + "Name: " + this.name + "\n" + "Size: " + this.size + "\n" + 
        this.price + "Facility: " + this.facility + "\n" + 
        "City: " + this.city + "\n" + "Address: " + this.address + "\n";
    }
    
    @Override
    public boolean read (String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
}

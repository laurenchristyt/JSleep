package LaurenChristyJSleepRJ;

public class Complaint extends Serializable
{
    public String desc;
    public String date;
    
    public Complaint(String date, String desc){
        this.date = date;
        this.desc = desc;
    }
    public String toString(){
        return "Date:" + this.date + "\n" + "Desc: " + this.desc + "\n";
    }
}

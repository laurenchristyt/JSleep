package LaurenChristyJSleepRJ;

public class Account extends Serializable implements FileParser
{
    public String name;
    public String email;
    public String password;
    
    public Account(int id, String name, String email, String password){
       super(id);
       this.name = name;
       this.email = email;
       this.password = password;
    }
    public String toString(){
        return "ID: " + id + "\n" + "Name: " + this.name + "\n" + "Email: " + this.email + "\n" + "Password: " + this.password + "\n";
    }
    
    @Override
    public boolean read (String content){
        return false;
    }
    @Override
    public Object write(){
        return null;
    }
}

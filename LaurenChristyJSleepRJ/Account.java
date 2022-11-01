package LaurenChristyJSleepRJ;


public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    
    public Account(String name, String email, String password){
       this.name = name;
       this.email = email;
       this.password = password;
    }
    public String toString(){
        return "Name: " + this.name + "\n" + "Email: " + this.email + "\n" + "Password: " + this.password + "\n";
    }
}

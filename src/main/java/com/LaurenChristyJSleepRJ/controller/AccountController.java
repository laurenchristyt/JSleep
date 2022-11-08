package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.Account;
import com.LaurenChristyJSleepRJ.Renter;
import org.springframework.web.bind.annotation.*;
import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @GetMapping
    String index() { return "account page"; }
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_&_*~]+(?:\\.[a-zA-Z0-9_&_*~]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD= "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(filepath=".scr/main/java/com/test.json", value=Account.class)
    public static JsonTable<Account> accountTable;

    @Override
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

    @PostMapping("/login")
    public Account login(@RequestParam String email, @RequestParam String password){
        for(Account account : getJsonTable()){
            if(account.email == email && account.password == password){
                return account;
            }
        }
        return null;
    }

    @PostMapping("/register")
    public Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password){
        if ((!name.isBlank())){
            if(REGEX_PATTERN_EMAIL.matcher(email).matches() && REGEX_PATTERN_PASSWORD.matcher(password).matches()){
                for(Account account : getJsonTable()){
                    if(account.email.equals(email)){
                        return null;
                    }
                }
                Account newAccount = new Account(name,email,password);
                accountTable.add(newAccount);
                return newAccount;
            }
        }
        return null;
    }
    @PostMapping("/{id}/registerStore")
    public Renter registerRenter(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber){
        for (Account account : accountTable){
            if(account.id == id){
                Renter renter = new Renter(name, address, phoneNumber);
                return renter;
            }
        }
        return null;
    }
    @PostMapping("/{id}/topUp")
    public boolean topUp(@PathVariable int id, @RequestParam double balance){
        for(Account account : accountTable){
            if(account.id == id){
                account.balance = 0.0;
                account.balance += balance;
                return true;
            }
        }
        return false;
    }
}
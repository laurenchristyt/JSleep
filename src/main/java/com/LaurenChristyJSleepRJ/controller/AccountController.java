package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.Account;
import com.LaurenChristyJSleepRJ.Algorithm;
import com.LaurenChristyJSleepRJ.Predicate;
import com.LaurenChristyJSleepRJ.Renter;
import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = "src/json/account.json")
    public static JsonTable<Account> accountTable;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+([.]?[a-zA-Z]+)*\\.[a-zA-Z]+(?!\\s)$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}(?!\\s)$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register (@RequestParam String name, @RequestParam String email, @RequestParam String password) {

        String generatedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return new Account(name, email, generatedPassword);
    }

    @Override
    public JsonTable<Account> getJsonTable() {
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
    @PostMapping("/{id}/registerRenter")
    public Renter registerRenter(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber)
    {
        Predicate<Account> predicate = object -> object.id == id;
        Account findAccount = Algorithm.find(getJsonTable(), predicate);
        if (findAccount.renter == null)
        {
            Renter newRenter = new Renter(name, address, phoneNumber);
            findAccount.renter = newRenter;
            return newRenter;
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    public boolean topUp(@PathVariable int id, @RequestParam double balance)
    {
        Predicate<Account> predicate = object -> object.id == id;
        Account findAccount = Algorithm.find(getJsonTable(), predicate);
        if (findAccount != null) {
            findAccount.balance += balance;
            return true;
        }
        return false;
    }

}
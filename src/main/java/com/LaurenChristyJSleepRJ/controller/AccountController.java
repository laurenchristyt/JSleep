package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    @JsonAutowired(value = Account.class, filepath = "src/json/account.json")
    public static JsonTable<Account> accountTable;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+([.]?[a-zA-Z]+)*\\.[a-zA-Z]+(?!\\s)$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}(?!\\s)$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/login")
    Account login
            (
                    @RequestParam String email,
                    @RequestParam String password
            ) {

        String encryptedPass = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            //to hexa format
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPass = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        String finalEncryptedPass = encryptedPass;
        return Algorithm.<Account>find(getJsonTable(), acc -> acc.email.equals(email) && acc.password.equals(finalEncryptedPass));
    }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            ) {
        if(!name.isBlank()) {
            if(REGEX_PATTERN_EMAIL.matcher(email).matches() &&
               REGEX_PATTERN_PASSWORD.matcher(password).matches()) {

                if(Algorithm.<Account>exists(getJsonTable(), acc -> acc.email.equals(email))) return null;

                String encryptedPass = null;
                try
                {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    //to hexa format
                    for (byte aByte : bytes) {
                        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
                    }
                    encryptedPass = sb.toString();
                }
                catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }

                Account newAcc = new Account(name, email, encryptedPass);
                accountTable.add(newAcc);
                return newAcc;
            }
        }
        return null;
    }

    @PostMapping("/{id}/registerRenter")
    Renter registerRenter
            (
                    @PathVariable int id,
                    @RequestParam String username,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            ) {
        Account findAcc = Algorithm.<Account>find(getJsonTable(), obj -> obj.id == id);
        if (findAcc.renter == null) {
            Renter newRenter = new Renter(username, address, phoneNumber);
            findAcc.renter = newRenter;
            return newRenter;
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp(
            @PathVariable int id,
            @RequestParam double balance
    ) {
        Account findAcc = Algorithm.<Account>find(getJsonTable(), obj -> obj.id == id);
        if (findAcc != null) {
                findAcc.balance += balance;
                return true;
            }
        return false;
    }

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

//    @GetMapping("/{id}")
//    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}

package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * This class represents an account for a user of the hotel booking system.
 * @author Lauren Christy Tanudjaja (2106707870)
 * @version (27-09-2022)
 */

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    @JsonAutowired(value = Account.class, filepath = "src/json/account.json")
    public static JsonTable<Account> accountTable;
    /**
     * REGEX for the email. Email should be in the format of local@domain, example : amandio@gmail.com
     * local only contains alphanumeric characters and can't contain
     * any special characters or whitespace. domain can't contain any number.
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+([.]?[a-zA-Z]+)*\\.[a-zA-Z]+(?!\\s)$";
    /**
     * REGEX for the password. Password should be at least 8 characters long and contain at least 1 uppercase letter,
     * 1 lowercase letter, 1 number, and no whitespace.
     */
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}(?!\\s)$";
    /**
     * Pattern for email REGEX.
     */
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    /**
     * Pattern for password REGEX.
     */
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @GetMapping
    String index() { return "account page"; }

    /**
     * Attempts to log in to the system using the registered email and password.
     *
     * @param email The email of the account.
     * @param password The password of the account.
     * @return The account object if the login is successful, or `null` otherwise.
     */
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

    /**
     * Register a new account to the system using the name, email and password.
     *
     * @param name The name the user wants to register.
     * @param email The email of the account.
     * @param password The password of the account.
     * @return The created `Account` object if the registration is successful, or `null` otherwise.
     */
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

    /**
     * Registers a new renter for the specified account.
     *
     *  @param id The ID of the account.
     *  @param username The username of the renter.
     *  @param address The address of the renter.
     *  @param phoneNumber The phone number of the renter.
     *  @return The created `Renter` object if the registration is successful, or `null` otherwise.
     */
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

    /**
     * Adds the specified amount to the balance of the specified account.
     *
     * @param id The ID of the account.
     * @param balance The amount to add to the balance of the account.
     * @return `true` if the balance was successfully updated, or `false` otherwise.
     */
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


    /**
     * Returns the `JsonTable` object that stores the account data.
     *
     * @return The `accountTable` object that stores the account data.
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }
}

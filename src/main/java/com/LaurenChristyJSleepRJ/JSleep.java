package com.LaurenChristyJSleepRJ;

import java.io.IOException;
import java.util.List;

import com.LaurenChristyJSleepRJ.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * CS6.
 *
 * @author Bernanda
 * @version 4-11-2022
 */
@SpringBootApplication
public class JSleep
{
    public static void main(String[] args) {
        JsonDBEngine.Run(JSleep.class);
        SpringApplication.run(JSleep.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }

}
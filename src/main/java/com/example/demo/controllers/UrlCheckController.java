package com.example.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "site is up.";
    private final String SITE_IS_DOWN = "site is down.";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check")
    public String getUrlStatutsMsg(@RequestParam String url) {
        String returnMsg = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() > 400)
                return SITE_IS_DOWN;
            returnMsg = SITE_IS_UP;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            returnMsg = INCORRECT_URL;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            returnMsg = SITE_IS_DOWN;
        }

        return returnMsg;
    }

}

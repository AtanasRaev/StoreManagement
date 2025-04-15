package com.storemanagement.service.impl;

import com.storemanagement.service.InternetCheckerService;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class InternetCheckerServiceImpl implements InternetCheckerService {
    @Override
    public boolean isInternetAvailable() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("HEAD");

            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            connection.connect();

            return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}

package com.inti.student.androidassignment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPDataHandler {
    static String stream = null;

    public String getHTTPData(String urlString) {
        try {
            URL url = new URL(urlString);  //Assigns the paramater as URL
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); //Starts internet connection

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) { //Checks if the status of the http response
                InputStream in = new BufferedInputStream(urlConnection.getInputStream()); //Reads the information
                BufferedReader reader = new BufferedReader(new InputStreamReader(in)); //Assigns the information into a reader
                StringBuilder builder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) //Assigns the reader into a String
                    builder.append(line); //Appends the string into a String Builder

                stream = builder.toString(); //Converts the value inside builder into a String
                urlConnection.disconnect(); //Ends internet connection
            }
        }

        catch (Exception e) {
            e.printStackTrace(); //Handles exceptions
        }

        return stream;

    }
}

package com.openWeatherAPI;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.*;

public class openWeatherAPI {
    private static HttpURLConnection connection;
    
    public double speed;

    public void main() {
        BufferedReader reader;
        JSONObject JsonObject;

        String readAPIResponse = " ";
        StringBuilder jsonString = new StringBuilder();

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?zip=75058,us&appid=d440f58a2880382910368d5464bc30c8");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            // System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while((readAPIResponse = reader.readLine()) != null){
                jsonString.append(readAPIResponse);
            }
            reader.close();

            //System.out.println(responseContent.toString());
            //JsonObject jsonObject = responseContent;
            //StringBuffer x = responseContent.;
            JSONObject jsonObj = new JSONObject(jsonString.toString());
            
            //System.out.println(jsonString);
            //System.out.println(jsonObj.getJSONObject("wind").get("speed"));
            
            this.speed = Double.parseDouble(jsonObj.getJSONObject("wind").get("speed").toString());

            //String x = parse(responseContent);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            connection.disconnect();
        }
    }
}
package com.toppr.utils;


import org.json.JSONArray;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class Authentication
{
    private static String USER_AGENT = "Mozilla/5.0";

    /**
     * Function for OTP code generated
     * @param email
     * @return OTP code
     */
    public static String getOTPCode(String email) throws Exception
    {
        URL url = new URL (JavaConstants.otpUrl + email);
        try {
            if (System.getProperty("env").equalsIgnoreCase("staging"))
                url = new URL (JavaConstants.stagingOtpUrl + email);
            else if (System.getProperty("env").equalsIgnoreCase("production"))
                url = new URL (JavaConstants.otpUrl + email);
            else if (System.getProperty("env").equalsIgnoreCase("preproduction"))
                url = new URL (JavaConstants.preProductionOtpUrl + email);
        }
        catch (Exception e)
        {
            url = new URL (JavaConstants.stagingOtpUrl + email);
            //url = new URL (JavaConstants.preProductionOtpUrl + email);
        }

        String auth = JavaConstants.otpUsername + ":" + JavaConstants.otpPassword;
        String encoding = new BASE64Encoder().encode(auth.getBytes());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        InputStream content = (InputStream) connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(content));
        String line, response;
        response = in.readLine();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("OTP Request: " + url);
        System.out.println("OTP Response: "+response);
        JSONArray jsonarray = new JSONArray(response);
        JSONObject jsonobject = jsonarray.getJSONObject(0);
        String code = jsonobject.getString("otp");
//        String code = (String) jsonObject.get("otp");
        System.out.println("OTP Code is: " + code);
        return code;
    }
}

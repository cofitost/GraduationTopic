package com.example.rabbit.login;

import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class PostOrganizers {

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    void httpPost(){
        SeminarOrganizers organizers = new SeminarOrganizers();

        String str_accountNumber = organizers.accountNumber.getText().toString();
        String str_username = organizers.username.getText().toString();
        String str_password = organizers.password.getText().toString();
        String str_email = organizers.email.getText().toString();
        String str_phone = organizers.phone.getText().toString();
        String str_principal = organizers.principal.getText().toString();

        try {
            URL url = new URL("https://www.google.com.tw/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            HashMap<String, String> postDataParams = new HashMap<>();

            postDataParams.put("accountNumber",str_accountNumber);
            postDataParams.put("username", str_username);
            postDataParams.put("password", str_password);
            postDataParams.put("email", str_email);
            postDataParams.put("phone", str_phone);
            postDataParams.put("principal", str_principal);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            StringBuilder sb = new StringBuilder();

            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpPost();
            }
        }).start();
    }
}

package com.example.rabbit.login;

import android.app.Activity;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class OrganizerThread {
    String str_accountNumber,str_username,str_password,str_email,str_phone,str_principal;
    Activity activity;

    OrganizerThread(Activity activity,String str_accountNumber,String str_username,String str_password,String str_principal,String str_email,String str_phone) {
        this.activity = activity;
        this.str_accountNumber = str_accountNumber;
        this.str_username = str_username;
        this.str_password = str_password;
        this.str_principal = str_principal;
        this.str_email = str_email;
        this.str_phone = str_phone;
    }


    public void run() {
        String url =
                "http://192.168.0.3:8080/android-backend/webapi/organizer/register" +
                        "androidgaedemo?" +
                        "str_accountNumber =" + str_accountNumber + "&" +
                        "str_username =" + str_username + "&" +
                        "str_password =" + str_password + "&" +
                        "str_principal =" + str_principal + "&" +
                        "str_email =" + str_email + "&" +
                        "str_phone =" + str_phone ;

        HttpGet request = new HttpGet(url);
        //String result = "";
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {

                Toast.makeText(activity,"成功",Toast.LENGTH_SHORT).show();

            } else {
                connerror();
            }
        } catch (Exception e) {
            connerror();
        }
    }

    void connerror() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "連線失敗",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}

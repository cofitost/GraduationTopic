package com.example.rabbit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CommonUser extends AppCompatActivity {

    EditText accountNumber,userName,password,email,phone;
    Button done,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountNumber = (EditText)findViewById(R.id.ET_signUp_accountNumber);
        userName = (EditText)findViewById(R.id.ET_signUp_name);
        password = (EditText)findViewById(R.id.ET_signUp_PWD);
        email = (EditText)findViewById(R.id.ET_signUp_email);
        phone = (EditText)findViewById(R.id.ET_signUp_phone);

        done = (Button)findViewById(R.id.BT_signUp_done);
        done.setOnClickListener(DO);

        cancel = (Button)findViewById(R.id.BT_signUp_cancel);
        cancel.setOnClickListener(CA);
    }

    public OnClickListener DO = new OnClickListener() {
        @Override
        public void onClick(View v) {

            onPost();

            Intent intent = new Intent();
            intent.setClass(CommonUser.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    public OnClickListener CA = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(CommonUser.this,SelectAccountType.class);
            startActivity(intent);
            finish();
        }
    };

    public void onPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpPost();
            }
        }).start();
    }

    public void httpPost(){
        String result = null;

        HttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost("140.134.26.71:2048");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("key",value));
            //params.add(new BasicNameValuePair("hour",postHour));
            params.add(new BasicNameValuePair("accountNumber",accountNumber.getText().toString()));
            params.add(new BasicNameValuePair("userName",userName.getText().toString()));
            params.add(new BasicNameValuePair("password",password.getText().toString()));
            params.add(new BasicNameValuePair("email",email.getText().toString()));
            params.add(new BasicNameValuePair("phone",phone.getText().toString()));

            UrlEncodedFormEntity ent = null;
            Log.d("abc",params.toString());

            ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                result = EntityUtils.toString(resEntity);
                Log.d("abcd",result);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent intent = new Intent();
            intent.setClass(CommonUser.this,SelectAccountType.class);
            startActivity(intent);
            finish();
        }

        return false;

    }

}

package com.example.rabbit.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    Button login,signUp,refresh;
    EditText id,password;
    String result = null;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        login = (Button)findViewById(R.id.BT_login);
        login.setOnClickListener(LO);

        signUp = (Button)findViewById(R.id.BT_signUp);
        signUp.setOnClickListener(SU);

        refresh = (Button)findViewById(R.id.BT_refresh);
        refresh.setOnClickListener(RE);

        id = (EditText)findViewById(R.id.ET_IDInput);

        password = (EditText)findViewById(R.id.ET_PWDInput);
        handler.post(check);
    }

    public OnClickListener LO = new OnClickListener() {
        @Override
        public void onClick(View v) {
            onPost();
        }
    };

    public OnClickListener SU = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,SelectAccountType.class);
            startActivity(intent);
            finish();
        }
    };

    public OnClickListener RE = new OnClickListener() {
        @Override
        public void onClick(View v) {
            id.setText("");
            password.setText("");
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

        HttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost("140.134.26.71:2048");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("key",value));
            //params.add(new BasicNameValuePair("hour",postHour));
            params.add(new BasicNameValuePair("accountNumber",id.getText().toString()));
            params.add(new BasicNameValuePair("password",password.getText().toString()));

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

    private Runnable check = new Runnable() {//結果寫在這
        @Override
        public void run() {
            if(result!=null) {
                handler.removeCallbacks(check);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MainInterface.class);
                startActivity(intent);
                finish();
            }
            else{
                handler.postDelayed(check,3000);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Sure to exit?");
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }

        return false;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

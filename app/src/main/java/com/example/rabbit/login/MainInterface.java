package com.example.rabbit.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.*;
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

public class MainInterface extends AppCompatActivity implements BeaconConsumer{

    protected static final String TAG = "MA";
    private BeaconManager bm;
    String UUID = "B5B182C7-EAB1-4988-AA99-B5C1517008D9";
    String Major = "1";
    String Minor = "39295";
    Region region1 = new Region("myMonitoringUniqueId",Identifier.parse(UUID),Identifier.parse(Major),Identifier.parse(Minor));
    TextView welcome;
    Toast toast;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        welcome = (TextView)findViewById(R.id.tv_welcome);

        bm = BeaconManager.getInstanceForApplication(this);
        bm.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        bm.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bm.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        bm.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                onPost();
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        welcome.setText("leave");
                        toast.makeText(MainInterface.this,"Beacon遠離",toast.LENGTH_LONG).show();
//stuff that updates ui
                    }
                });
                Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+i);
            }
        });

        try {
            bm.startMonitoringBeaconsInRegion(region1);
        } catch (RemoteException e) {    }
    }

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
            params.add(new BasicNameValuePair("Minor",Minor));

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
            intent.setClass(MainInterface.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        return false;

    }

}

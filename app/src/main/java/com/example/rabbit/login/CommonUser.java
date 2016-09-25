package com.example.rabbit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class CommonUser extends AppCompatActivity {

    Button done,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        done = (Button)findViewById(R.id.BT_signUp_done);
        done.setOnClickListener(DO);

        cancel = (Button)findViewById(R.id.BT_signUp_cancel);
        cancel.setOnClickListener(CA);
    }

    public OnClickListener DO = new OnClickListener() {
        @Override
        public void onClick(View v) {
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

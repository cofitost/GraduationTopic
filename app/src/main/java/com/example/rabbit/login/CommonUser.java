package com.example.rabbit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class CommonUser extends AppCompatActivity {

    EditText accountNumber,username,password,email,phone;
    Button done,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountNumber = (EditText)findViewById(R.id.ET_signUp_accountNumber);
        username = (EditText)findViewById(R.id.ET_signUp_name);
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

            PostCommon post = new PostCommon();
            post.onPost();

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

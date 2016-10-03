package com.example.rabbit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SeminarOrganizers extends AppCompatActivity {

    EditText accountNumber,username,password,principal,email,phone;
    Button done,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_organizers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountNumber = (EditText)findViewById(R.id.ET_organizersignUp_accountNumber);
        username = (EditText)findViewById(R.id.ET_organizersignUp_name);
        password = (EditText)findViewById(R.id.ET_organizersignUp_PWD);
        email = (EditText)findViewById(R.id.ET_organizersignUp_email);
        phone = (EditText)findViewById(R.id.ET_organizersignUp_phone);
        principal = (EditText)findViewById(R.id.ET_organizersignUp_principal);

        done = (Button)findViewById(R.id.BT_organizersignUp_done);
        done.setOnClickListener(DO);

        cancel = (Button)findViewById(R.id.BT_organizersignUp_cancel);
        cancel.setOnClickListener(CA);
    }

    public View.OnClickListener DO = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String str_accountNumber = accountNumber.getText().toString();
            String str_username = username.getText().toString();
            String str_password = password.getText().toString();
            String str_email = email.getText().toString();
            String str_phone = phone.getText().toString();
            String str_principal = principal.getText().toString();

            OrganizerThread thread = new OrganizerThread(SeminarOrganizers.this,str_accountNumber,str_username,str_password,str_principal,str_email,str_phone);
            //thread.start();

            Intent intent = new Intent();
            intent.setClass(SeminarOrganizers.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    public View.OnClickListener CA = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SeminarOrganizers.this,SelectAccountType.class);
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
            intent.setClass(SeminarOrganizers.this,SelectAccountType.class);
            startActivity(intent);
            finish();
        }

        return false;

    }

}

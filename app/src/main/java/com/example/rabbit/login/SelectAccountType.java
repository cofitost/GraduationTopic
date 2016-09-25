package com.example.rabbit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class SelectAccountType extends AppCompatActivity {

    Button commonUser,seminarOrganizers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        commonUser = (Button)findViewById(R.id.BT_commonUser);
        commonUser.setOnClickListener(CU);

        seminarOrganizers = (Button)findViewById(R.id.BT_seminarOrganizers);
        seminarOrganizers.setOnClickListener(SO);
    }

    public OnClickListener CU = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SelectAccountType.this,CommonUser.class);
            startActivity(intent);
            finish();
        }
    };

    public OnClickListener SO = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SelectAccountType.this,SeminarOrganizers.class);
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
            intent.setClass(SelectAccountType.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        return false;

    }

}

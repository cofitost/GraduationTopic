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

public class SeminarOrganizers extends AppCompatActivity {

    Button done,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_organizers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        done = (Button)findViewById(R.id.BT_organizersignUp_done);
        done.setOnClickListener(DO);

        cancel = (Button)findViewById(R.id.BT_organizersignUp_cancel);
        cancel.setOnClickListener(CA);
    }

    public View.OnClickListener DO = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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

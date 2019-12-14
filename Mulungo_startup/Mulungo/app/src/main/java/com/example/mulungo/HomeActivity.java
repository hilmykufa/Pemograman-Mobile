package com.example.mulungo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    private TextView name, email;
    private Button btn_logout;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        HashMap<String, String> user = sessionManager.getUSerDetail();
        String mName = user.get(SessionManager.NAME);
        String mEmail = user.get(SessionManager.EMAIL);
        name.setText(mName);
        email.setText(mEmail);
        //Intent intent = getIntent();
        //String extraName = intent.getStringExtra("name");
       // String extraMail = intent.getStringExtra("email");

        //name.setText(extraName);
       // email.setText(extraMail);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

    }
}

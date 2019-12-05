package com.example.mulungo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView name, email;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        Intent intent = getIntent();
        String extraName = intent.getStringExtra("name");
        String extraMail = intent.getStringExtra("email");
        name.setText(extraName);
        email.setText(extraMail);

    }
}

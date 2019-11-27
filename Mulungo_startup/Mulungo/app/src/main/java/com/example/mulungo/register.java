package com.example.mulungo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    private EditText name,email, password, c_password;
    private Button btn_regist;
    private ProgressBar loading;
    private static  String URL_REGIST = "http://192.168.1.66/android_register_login/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
            loading=findViewById(R.id.loading);
            name=findViewById(R.id.Name);
            email=findViewById(R.id.Email);
            password=findViewById(R.id.Password);
            c_password=findViewById(R.id.C_Password);
            btn_regist=findViewById(R.id.btn_regist);
            btn_regist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Regist();
                }
            });
    }
    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);
        final String name = this.name.getText().toString().trim();
        final String email= this.email.getText().toString().trim();
        final String password= this.password.getText().toString().trim();
        final String c_password= this.c_password.getText().toString().trim();
        StringRequest stringrequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject= new JSONObject(response);
                            String success = jsonobject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(register.this, "Register Success",Toast.LENGTH_SHORT);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(register.this, "Register Error" + e.toString(),Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this, "Register Error " + error.toString(),Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("name", name);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }
}

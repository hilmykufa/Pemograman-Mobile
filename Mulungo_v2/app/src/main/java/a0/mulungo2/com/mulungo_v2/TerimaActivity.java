package a0.mulungo2.com.mulungo_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TerimaActivity extends AppCompatActivity {
    //private EditText email,password;
   //private Button btn_login;
    private Button btn_kirim;
    //private ProgressBar loading;
    private static String URL_LOGIN="http://192.168.1.66/android_register_login/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terima_sampah);
        sessionManager = new SessionManager(this);
        //loading=findViewById(R.id.loading);
        HashMap<String, String> user = sessionManager.getUSerDetail();
        final String mName = user.get(sessionManager.NAME);
        final String mEmail = user.get(sessionManager.EMAIL);
        btn_kirim=findViewById(R.id. btn_kirim);
        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEmail.isEmpty()|| !mName.isEmpty()){
                    //kirim(mEmail,mName);
                }else {
                   // mEmail.setError("Please insert email");
                   // mName.setError("Please insert Password");
                }
            }
        });
       /* btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,register.class);
                startActivity(intent);
            }
        });
        */
    }
    private void  kirim(final String email, final String name, final String id  ){
        //loading.setVisibility(View.VISIBLE);
        btn_kirim.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    sessionManager.createSession(name,email);
                                    Intent intent = new Intent(TerimaActivity.this,HomeActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                    finish();
                                    //loading.setVisibility(View.GONE);
                                    btn_kirim.setVisibility(View.VISIBLE);
                                    //Toast.makeText(loginActivity.this,"succes"+name,Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                //loading.setVisibility(View.GONE);
                                btn_kirim.setVisibility(View.VISIBLE);
                                Toast.makeText(TerimaActivity.this,"succes", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //loading.setVisibility(View.GONE);
                            btn_kirim.setVisibility(View.VISIBLE);
                            Toast.makeText(TerimaActivity.this, "Login Error1" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TerimaActivity.this, "Login Error mas" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("name",name);
                params.put("id",id);
                params.put("size",id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

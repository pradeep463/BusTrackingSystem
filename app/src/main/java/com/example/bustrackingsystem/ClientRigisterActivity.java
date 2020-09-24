package com.example.bustrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class ClientRigisterActivity extends AppCompatActivity {

    MaterialEditText userName,email,password,mobile;
    RadioGroup radioGroup;
    Button rigister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_rigister);



        userName = findViewById(R.id.edt_userName);
        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        mobile = findViewById(R.id.edt_mobile);
        radioGroup = findViewById(R.id.rdg_radiogrp);
        rigister = findViewById(R.id.btn_rigister);
        rigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getUserName = userName.getText().toString();
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                String getMobile = mobile.getText().toString();
                if(TextUtils.isEmpty(getUserName) || TextUtils.isEmpty(getEmail)
                        ||TextUtils.isEmpty(getPassword) || TextUtils.isEmpty(getMobile)){
                    Toast.makeText(getApplicationContext(),"All Fields are Reqired",Toast.LENGTH_SHORT).show();
                }
                else{
                    int genderId = radioGroup.getCheckedRadioButtonId();
                    RadioButton selectedGender = radioGroup.findViewById(genderId);
                    if(selectedGender == null){
                        Toast.makeText(getApplicationContext(),"Select Gender Please",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String selectGender = selectedGender.getText().toString();

                        rigisterNewAccount(getUserName,getEmail,getPassword,getMobile,selectGender);
                    }

                }
            }
        });



    }


    private void rigisterNewAccount(final String userName, final String email, final String password, final String mobile, final String gender){
        final ProgressDialog progressDialog = new ProgressDialog(ClientRigisterActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Regigstering New Account");
        progressDialog.show();
        String uRI = "http://192.168.1.101/phppro/andpro/rigi.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("Successfully Registered")){
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ClientRigisterActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("username",userName);
                param.put("email",email);
                param.put("password",password);
                param.put("mobile",mobile);
                param.put("gender",gender);
                return param;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(ClientRigisterActivity.this).addToRequestQueue(request);



    }


}

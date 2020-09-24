package com.example.bustrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;


public class ClientActivity extends AppCompatActivity {
    private static Context mContext;

    MaterialEditText email,password;


    Button rigister,login ; //
    // CheckBox loginState;
    // SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        //  sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        email =  findViewById(R.id.edt_logEmail);
        password = findViewById(R.id.edt_logPassword);
        rigister =  findViewById(R.id.btn_clientRigister);
      //  login = findViewById(R.id.btn_clientLogin) ;
        mContext = this;



//rigister button

        rigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClientActivity.this,ClientRigisterActivity.class);
                startActivity(i);
                finish();
            }
        });



        //full code of login that have errors
      /*  login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                if(TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword)){
                    Toast.makeText(getApplicationContext(),"All Firlds are Reqired",Toast.LENGTH_SHORT).show();
                }
                else {
                    login(getEmail,getPassword);
                }
            }
        });

        String loginStutus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");

        if(loginStutus.equals("loggedin")){
            Intent i = new Intent(MainActivity.this,AppStartActivity.class);
            startActivity(i);
        }

      }

      private void login(final String email, final String password){
          final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
          progressDialog.setCancelable(false);
          progressDialog.setIndeterminate(false);
          progressDialog.setTitle("Login");
          progressDialog.show();
          String uRI = "http://192.168.1.101/phppro/andpro/login.php";


          StringRequest request = new StringRequest(Request.Method.POST, uRI, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                  if(response.equals("Login Success")){
                      progressDialog.dismiss();
                      Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                      SharedPreferences.Editor editor = sharedPreferences.edit();
                     if(loginState.isChecked()){
                         editor.putString(getResources().getString(R.string.prefLoginState),"loggedin");
                     }
                     else{
                         editor.putString(getResources().getString(R.string.prefLoginState),"loggediout");
                     }

                     editor.apply();

                      Intent i = new Intent(MainActivity.this,AppStartActivity.class);
                      startActivity(i);
                  }
                  else{
                      progressDialog.dismiss();
                      Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                  }

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

              }
          }){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  HashMap<String,String> param = new HashMap<>();

                  param.put("email",email);
                  param.put("password",password);

                  return param;
              }

          };

          request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
          MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);*/

    }

    public static void openNewActivity(){

        Intent i = new Intent(mContext.getApplicationContext(),CliientMainActivity.class);
        mContext.startActivity(i);

    }


    public void loginBtn(View view) {


        String getEmail = email.getText().toString();
        String getPassword = password.getText().toString();


        //to check fields emty or not

        if(TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword)){
            Toast.makeText(getApplicationContext(),"All Firlds are Reqired",Toast.LENGTH_SHORT).show();
        }
        else {
            ClientBackground bg = new ClientBackground((View.OnClickListener) this);
            bg.execute(getEmail,getPassword);

        }


    }
}

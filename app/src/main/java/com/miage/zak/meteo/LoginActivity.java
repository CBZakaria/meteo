package com.miage.zak.meteo;

import android.content.Intent;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mLogin;
    private EditText mPassword;
    private Button mConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = findViewById(R.id.email_edt);
        mPassword = findViewById(R.id.password_edt);
        mConnection = findViewById(R.id.connection_btn);

        mConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLogin.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir votre login",Toast.LENGTH_SHORT ).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mLogin.getText().toString()).matches()){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir votre mail",Toast.LENGTH_SHORT ).show();
                }
                else {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}

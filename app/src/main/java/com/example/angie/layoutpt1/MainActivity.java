package com.example.angie.layoutpt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public enum LoginSuccess {
        login(1),
        password(2),
        success(0);

        private int intValue;

        LoginSuccess(int value) {
            intValue = value;
        }

        public int getIntValue() {
            return intValue;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textLogin =  findViewById(R.id.txtUser);
        final TextView textPassword =  findViewById(R.id.txtPass);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toastError = null;

                switch (checkLogin(textLogin.getText().toString(), textPassword.getText().toString())){
                    case login:
                        toastError.makeText(getApplicationContext(), getString(R.string.errMessageLogin)
                        ,toastError.LENGTH_LONG).show();
                        textLogin.requestFocus();
                        break;
                    case password:
                        toastError.makeText(getApplicationContext(), getString(R.string.errMessagePassword)
                                ,toastError.LENGTH_LONG).show();
                        textPassword.requestFocus();
                        break;
                    default:
                        startActivity(new Intent(MainActivity.this, MainClassList.class));
                        break;
                }
            }
        });
    }

    LoginSuccess checkLogin(String txtLogin, String txtPassword)
    {
        String holdLogin = "Angie";
        String holdPassword = "password";

        if(!(holdLogin.equals(txtLogin)))
            return LoginSuccess.login;
        if(!(holdPassword.equals(txtPassword)))
            return LoginSuccess.password;
        return LoginSuccess.success;
    }
}
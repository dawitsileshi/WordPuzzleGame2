package com.example.wordpuzzlegame.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wordpuzzlegame.Parent;
import com.example.wordpuzzlegame.ParentActivity;
import com.example.wordpuzzlegame.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et_activity_login_email,
            et_activity_login_password;

    private Button button_activity_login;

    private Parent parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_activity_login_email = findViewById(R.id.et_activity_login_uname);
        et_activity_login_password = findViewById(R.id.et_activity_login_password);

        button_activity_login = findViewById(R.id.button_activity_login);

        parent = new Parent(this);

        boolean exists = parent.checkParent();

        if(!exists) {
            startActivity(new Intent(this, CreateAccountActivity.class));
            finish();
        }

        button_activity_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_activity_login_email.getText().toString().trim();
                String password = et_activity_login_password.getText().toString().trim();

                if(checkInserted(email, password)) {
                    int result = parent.loginParent(email, password);
                    if(result == 1) {
                        startActivity(new Intent(LoginActivity.this, ParentActivity.class));
                    } else if(result == 0) {
                        Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    } else if(result == -1) {
                        Toast.makeText(LoginActivity.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkInserted(String email, String password) {
        return email.length() != 0 && password.length() != 0;
    }
}

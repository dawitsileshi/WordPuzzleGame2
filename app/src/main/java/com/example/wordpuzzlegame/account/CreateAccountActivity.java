package com.example.wordpuzzlegame.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//import com.example.wordpuzzlegame.Parent;
import com.example.wordpuzzlegame.R;
import com.example.wordpuzzlegame.model.Parent;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText et_activity_create_account_name,
            et_activity_create_account_email,
            et_activity_create_account_password,
            et_activity_create_account_confirm_password;

    private RadioGroup rg_activity_create_account_gender,
                        rg_activity_create_account_relation;

    private RadioButton rb_activity_create_account_male,
                        rb_activity_create_account_female,
                        rb_activity_create_account_mother,
                        rb_activity_create_account_father;

    private Button button_activity_create_account_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        et_activity_create_account_name = findViewById(R.id.et_activity_add_parent_name);
        et_activity_create_account_email = findViewById(R.id.et_activity_add_parent_email);
        et_activity_create_account_password = findViewById(R.id.et_activity_add_parent_password);
        et_activity_create_account_confirm_password = findViewById(R.id.et_activity_add_parent_confirm_password);
        rg_activity_create_account_gender = findViewById(R.id.rg_activity_add_parent_gender);
        rg_activity_create_account_relation = findViewById(R.id.rg_activity_add_parent_relation);
        rb_activity_create_account_male = findViewById(R.id.rb_activity_add_parent_male);
        rb_activity_create_account_female = findViewById(R.id.rb_activity_add_parent_female);
        rb_activity_create_account_mother = findViewById(R.id.rb_activity_add_parent_mother);
        rb_activity_create_account_father = findViewById(R.id.rb_activity_add_parent_father);
        button_activity_create_account_save = findViewById(R.id.button_activity_create_account_save);

        button_activity_create_account_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_activity_create_account_name.getText().toString().trim();

                int genderRadioButtonId = rg_activity_create_account_gender.getCheckedRadioButtonId();
                RadioButton genderRadioButton = findViewById(genderRadioButtonId);

                int relationRadioButtonId = rg_activity_create_account_relation.getCheckedRadioButtonId();
                RadioButton relationRadioButton = findViewById(relationRadioButtonId);

                String email = et_activity_create_account_email.getText().toString().trim();
                String password = et_activity_create_account_password.getText().toString().trim();
                String confirm_password = et_activity_create_account_confirm_password.getText().toString().trim();

                if(checkInserted(name, genderRadioButton, relationRadioButton, email, password, confirm_password)) {
                    if(passwordMatches(password, confirm_password)) {

                        Parent singleParent = new Parent(name, genderRadioButton.getText().toString(), relationRadioButton.getText().toString(), email, password);
                        Parent parent = new Parent(CreateAccountActivity.this);
                        parent.register(singleParent);
                        Toast.makeText(CreateAccountActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));

                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Passwords Don't Match.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Please, Insert all the Data.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean passwordMatches(String password, String confirm_password) {
        return password.equals(confirm_password);
    }

    private boolean checkInserted(String name, RadioButton genderRadioButton, RadioButton relationRadioButton, String email, String password, String confirm_password) {
        return name.length() != 0 && genderRadioButton != null && relationRadioButton != null && email.length() != 0 && password.length() != 0 && confirm_password.length() != 0;
    }
}

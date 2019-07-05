package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Kid;

import java.util.ArrayList;

public class AddKidActivity extends AppCompatActivity {

    private EditText et_activity_add_kid;

    private RadioGroup rg_activity_add_kid;

    private RadioButton rb_activity_add_kid_male,
            rb_activity_add_kid_female;

    private AppCompatSpinner acp_activity_add_kid_grade;

    private Button button_activity_add_kid;

    private String grade;

    private Kid kid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);

        kid = new Kid(this);

        et_activity_add_kid = findViewById(R.id.et_activity_add_kid_name);

        rg_activity_add_kid = findViewById(R.id.rg_activity_add_kid_gender);

        rb_activity_add_kid_male = findViewById(R.id.rb_activity_add_kid_male);
        rb_activity_add_kid_female = findViewById(R.id.rb_activity_add_kid_female);

        acp_activity_add_kid_grade = findViewById(R.id.acp_activity_add_kid);

        button_activity_add_kid = findViewById(R.id.button_activity_add_kid_save);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acp_activity_add_kid_grade.setAdapter(adapter);

        acp_activity_add_kid_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                grade = (String) parent.getSelectedItem();
            }
        });

        button_activity_add_kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_activity_add_kid.getText().toString().trim();
                int genderRadioButtonId = rg_activity_add_kid.getCheckedRadioButtonId();
                RadioButton genderRadioButton = findViewById(genderRadioButtonId);

                if(allInserted(name, genderRadioButton, grade)) {
                    Kid kid = new Kid(name, genderRadioButton.getText().toString(), grade);
                    long result = AddKidActivity.this.kid.addKid(kid);
                    if(result != -1) {
                        Toast.makeText(AddKidActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddKidActivity.this, KidListActivity.class));
                        finish();
                    } else {
                        Toast.makeText(AddKidActivity.this, "Something is wrong with the saving, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(AddKidActivity.this, String.valueOf(grade), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean allInserted(String name, RadioButton genderRadioButton, String grade) {
        return name.length() != 0 && genderRadioButton != null && grade != null;
    }
}

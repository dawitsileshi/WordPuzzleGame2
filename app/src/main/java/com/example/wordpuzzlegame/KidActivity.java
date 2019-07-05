package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Kid;
import com.example.wordpuzzlegame.utils.PreferenceUtil;

public class KidActivity extends AppCompatActivity implements LeavePageDialog.LeaveAccepted {

    PreferenceUtil preferenceUtil;

    private TextView tv_activity_kid_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid);

        tv_activity_kid_name = findViewById(R.id.tv_activity_kid_name);

        preferenceUtil = new PreferenceUtil(this);

        preferenceUtil.saveBooleanValue(false, PreferenceUtil.PARENT_ACTIVE);

        long activeKidId = preferenceUtil.retrieveLongValue(PreferenceUtil.ACTIVE_USER_ID);

        Kid kid = new Kid(this).kid(activeKidId);

        tv_activity_kid_name.setText(kid.getKid_name());

        Log.i("active student", String.valueOf(activeKidId));

        new PreferenceUtil(this).saveBooleanValue(false, PreferenceUtil.PARENT_ACTIVE);

        findViewById(R.id.cv_activity_kid_study).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity(StudyWordActivity.class);
            }
        });

        findViewById(R.id.cv_activity_kid_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity(ChooseQuizActivity.class);
            }
        });

        findViewById(R.id.cv_activity_kid_high_score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KidActivity.this, "NOt Available", Toast.LENGTH_SHORT).show();
//                startAnotherActivity(StudyWordActivity.class);
            }
        });
    }

    private void startAnotherActivity(Class aClass) {

        startActivity(new Intent(this, aClass));

    }

    @Override
    public void onBackPressed() {
        LeavePageDialog leavePageDialog = new LeavePageDialog();
        leavePageDialog.show(getSupportFragmentManager(), "leave page");
//        Toast.makeText(this, "Sorry you are not allowed to go back", Toast.LENGTH_SHORT).show();
//        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAccepted() {
        startActivity(new Intent(this, KidListActivity.class));
        new PreferenceUtil(this).saveBooleanValue(true, PreferenceUtil.PARENT_ACTIVE);
        finish();
    }
}

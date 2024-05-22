package com.example.recyclingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends Activity implements View.OnClickListener {

    TextView signup_btn;                 // 회원가입 버튼
    LinearLayout login_btn;                // 로그인 버튼

    EditText id_edit;                // id 에디트
    EditText pw_edit;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        signup_btn = (TextView)findViewById(R.id.signup_button);    // 회원가입 버튼을 찾고
        login_btn = (LinearLayout)findViewById(R.id.login_button);

        signup_btn.setOnClickListener(this);                 // 리스너를 달아줌.
        login_btn.setOnClickListener(this);                // 리스너를 달아줌.

        id_edit = (EditText)findViewById(R.id.id_edit);    // id 에디트를 찾음.
        pw_edit = (EditText)findViewById(R.id.password_edit);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.signup_button) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.login_button) {
            Intent intent2 = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent2);
            finish();
        }
    }
}

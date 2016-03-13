package com.example.administrator.test21;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.dao.pwdDAO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText txtlogin;
    private Button btnlogin,btnclose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtlogin=(EditText)findViewById(R.id.txtLogin);
        btnlogin=(Button)findViewById(R.id.btnLogin);
        btnclose=(Button)findViewById(R.id.btnClose);
        btnlogin.setOnClickListener(this);
        btnclose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Intent intent=new Intent(MainActivity.this,Main.class);
                pwdDAO pwdDAO=new pwdDAO(MainActivity.this);
               if((pwdDAO.getCount()==0||pwdDAO.find().getPassword().isEmpty())&&txtlogin.getText().toString().isEmpty()){
                    startActivity(intent);


                }else {
                    if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();

                    }
                }
                txtlogin.setText("");break;
            case R.id.btnClose:
            {
                finish();
            }

        }
    }
}

package com.example.administrator.test21;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/3/1.

*/

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mingrisoft.dao.pwdDAO;
import com.mingrisoft.model.Tb_pwd;

public class Sysset extends AppCompatActivity {
    EditText txtpwd;// 创建EditText对象
    Button btnSet, btnsetCancel;// 创建两个Button对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysset);// 设置布局文件

        txtpwd = (EditText) findViewById(R.id.txtPwd);// 获取密码文本框
        btnSet = (Button) findViewById(R.id.btnSet);// 获取设置按钮
        btnsetCancel = (Button) findViewById(R.id.btnsetCancel);// 获取取消按钮
        btnSet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdDAO pwdDAO=new pwdDAO(Sysset.this);
                Tb_pwd tb_pwd=new Tb_pwd(txtpwd.getText().toString());
                if(pwdDAO.getCount()==0){
                    pwdDAO.add(tb_pwd);
                }
                else {
                    pwdDAO.update(tb_pwd);
                }
                Toast.makeText(Sysset.this,"【密码】设置成功！",Toast.LENGTH_SHORT).show();
            }
        });
        btnsetCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                txtpwd.setText("");
                txtpwd.setHint("请输入密码");
            }
        });
    }
}
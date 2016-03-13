package com.example.administrator.test21;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mingrisoft.dao.flagDAO;
import com.mingrisoft.model.Tb_flag;

/**
 * Created by Administrator on 2016/3/1.
 */
public class Accountflag extends AppCompatActivity {
    private EditText txtFlag;
    private Button btnflagSaveButton,btnflagCancelButton;
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountflag);
        Log.e("1", "1");
        txtFlag = (EditText) findViewById(R.id.txtFlag);// 获取便签文本框
        btnflagSaveButton = (Button) findViewById(R.id.btnflagSave);// 获取保存按钮
        btnflagCancelButton = (Button) findViewById(R.id.btnflagCancel);// 获取取消按钮
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {// 为保存按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String strFlag = txtFlag.getText().toString();// 获取便签文本框的值
                if (!strFlag.isEmpty()) {// 判断获取的值不为空
                    flagDAO flagDAO = new flagDAO(Accountflag.this);// 创建FlagDAO对象
                    Tb_flag tb_flag = new Tb_flag(
                            flagDAO.getMaxId() + 1, strFlag);// 创建Tb_flag对象
                    flagDAO.add(tb_flag);// 添加便签信息
                    // 弹出信息提示
                    Toast.makeText(Accountflag.this, "〖新增便签〗数据添加成功！",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Accountflag.this, "请输入便签！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {// 为取消按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtFlag.setText("");// 清空便签文本框
            }
        });
    }
}

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountflag);

        txtFlag = (EditText) findViewById(R.id.txtFlag);// 获取便签文本框
        btnflagSaveButton = (Button) findViewById(R.id.btnflagSave);// 获取保存按钮
        btnflagCancelButton = (Button) findViewById(R.id.btnflagCancel);// 获取取消按钮
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {// 为保存按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String strFlag = txtFlag.getText().toString();// 获取便签文本框的值
                if (!strFlag.isEmpty()) {// 判断获取的值不为空
                    flagDAO flagDAO = new flagDAO(Accountflag.this);// 创建FlagDAO对象
                    Tb_flag tb_flag = new Tb_flag(
                            flagDAO.getMaxId() + 1, strFlag);// 创建Tb_flag对象
                    flagDAO.add(tb_flag);// 添加便签信息


                    Log.e("123","123");
                    // 弹出信息提示
                    Toast.makeText(Accountflag.this, "〖新增便签〗数据添加成功！",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Accountflag.this, "请输入便签！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {// 为取消按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtFlag.setText("");// 清空便签文本框
            }
        });
    }
}



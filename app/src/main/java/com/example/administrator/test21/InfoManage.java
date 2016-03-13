package com.example.administrator.test21;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.ThemedSpinnerAdapter;
import com.mingrisoft.dao.inaccountDAO;
import com.mingrisoft.dao.outaccountDAO;
import com.mingrisoft.model.Tb_inaccount;
import com.mingrisoft.model.Tb_outaccount;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/3/3.
 */
public class InfoManage extends AppCompatActivity {

    protected static final int DATE_DIALOG_ID = 0;// 创建日期对话框常量
    TextView tvtitle, textView;// 创建两个TextView对象
    EditText txtMoney, txtTime, txtHA, txtMark;// 创建4个EditText对象
    Spinner spType;// 创建Spinner对象
    Button btnEdit, btnDel;// 创建两个Button对象
    String[] strInfos;// 定义字符串数组
    String strid, strType;// 定义两个字符串变量，分别用来记录信息编号和管理类型

    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日

    outaccountDAO outaccountDAO = new outaccountDAO(InfoManage.this);// 创建OutaccountDAO对象
    inaccountDAO inaccountDAO = new inaccountDAO(InfoManage.this);// 创建InaccountDAO对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomanage);
        tvtitle = (TextView) findViewById(R.id.inouttitle);// 获取标题标签对象
        textView = (TextView) findViewById(R.id.tvInOut);// 获取地点/付款方标签对象
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney);// 获取金额文本框
        txtTime = (EditText) findViewById(R.id.txtInOutTime);// 获取时间文本框
        spType = (Spinner) findViewById(R.id.spInOutType);// 获取类别下拉列表
        txtHA = (EditText) findViewById(R.id.txtInOut);// 获取地点/付款方文本框
        txtMark = (EditText) findViewById(R.id.txtInOutMark);// 获取备注文本框
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        strInfos = bundle.getStringArray(Showinfo.FLAG);// 获取Bundle中记录的信息
        strid=strInfos[0];
        strType=strInfos[1];
        if (strType.equals("btnoutinfo"))// 如果类型是btnoutinfo
        {
            tvtitle.setText("支出管理");// 设置标题为“支出管理”
            textView.setText("地  点：");// 设置“地点/付款方”标签文本为“地 点：”

       ///
            //将可选内容与ArrayAdapter连接起来
           String[] outtype1 = getResources().getStringArray(R.array.outtype);
            ArrayAdapter adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,outtype1);
          //设置下拉列表的风格
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          //将adapter 添加到spinner中
            spType.setAdapter(adapter1);



            // 根据编号查找支出信息，并存储到Tb_outaccount对象中
            Tb_outaccount tb_outaccount = outaccountDAO.find(Integer
                    .parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));// 显示金额
            txtTime.setText(tb_outaccount.getTime());// 显示时间
            spType.setPrompt(tb_outaccount.getType());// 显示类别
            txtHA.setText(tb_outaccount.getAddress());// 显示地点
            txtMark.setText(tb_outaccount.getMark());// 显示备注
        } else if (strType.equals("btnininfo"))// 如果类型是btnininfo
        {
            tvtitle.setText("收入管理");// 设置标题为“收入管理”
            textView.setText("付款方：");// 设置“地点/付款方”标签文本为“付款方：”



            ///
            // 建立数据源
             String[] intype1 = getResources().getStringArray(R.array.intype);
            //将可选内容与ArrayAdapter连接起来
            ArrayAdapter adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,intype1);
            //设置下拉列表的风格
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //将adapter 添加到spinner中
            spType.setAdapter(adapter2);

            // 根据编号查找收入信息，并存储到Tb_outaccount对象中
            Tb_inaccount tb_inaccount = inaccountDAO.find(Integer
                    .parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));// 显示金额



            txtTime.setText(tb_inaccount.getTime());// 显示时间
            spType.setPrompt(tb_inaccount.getType());// 显示类别
            txtHA.setText(tb_inaccount.getHandler());// 显示付款方
            txtMark.setText(tb_inaccount.getMark());// 显示备注
        }

        txtTime.setOnClickListener(new View.OnClickListener() {// 为时间文本框设置单击监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);// 显示日期选择对话框
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {// 为修改按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (strType.equals("btnoutinfo"))// 判断类型如果是btnoutinfo
                {
                    Tb_outaccount tb_outaccount = new Tb_outaccount();// 创建Tb_outaccount对象
                    tb_outaccount.set_id(Integer.parseInt(strid));// 设置编号
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney
                            .getText().toString()));// 设置金额
                    tb_outaccount.setTime(txtTime.getText().toString());// 设置时间
                    tb_outaccount.setType(spType.getSelectedItem().toString());// 设置类别
                    tb_outaccount.setAddress(txtHA.getText().toString());// 设置地点
                    tb_outaccount.setMark(txtMark.getText().toString());// 设置备注
                    outaccountDAO.update(tb_outaccount);// 更新支出信息
                } else if (strType.equals("btnininfo"))// 判断类型如果是btnininfo
                {
                    Tb_inaccount tb_inaccount = new Tb_inaccount();// 创建Tb_inaccount对象
                    tb_inaccount.set_id(Integer.parseInt(strid));// 设置编号
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText()
                            .toString()));// 设置金额
                    tb_inaccount.setTime(txtTime.getText().toString());// 设置时间
                    tb_inaccount.setType(spType.getSelectedItem().toString());// 设置类别
                    tb_inaccount.setHandler(txtHA.getText().toString());// 设置付款方
                    tb_inaccount.setMark(txtMark.getText().toString());// 设置备注
                    inaccountDAO.update(tb_inaccount);// 更新收入信息
                }
                // 弹出信息提示
                Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {// 为删除按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (strType.equals("btnoutinfo"))// 判断类型如果是btnoutinfo
                {
                    outaccountDAO.delete(Integer.parseInt(strid));// 根据编号删除支出信息
                } else if (strType.equals("btnininfo"))// 判断类型如果是btnininfo
                {
                    inaccountDAO.delete(Integer.parseInt(strid));// 根据编号删除收入信息
                }
                Toast.makeText(InfoManage.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        final Calendar c = Calendar.getInstance();// 获取当前系统日期
        mYear = c.get(Calendar.YEAR);// 获取年份
        mMonth = c.get(Calendar.MONTH);// 获取月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取天数
        updateDisplay();// 显示当前系统时间
    }

    @Override
    protected Dialog onCreateDialog(int id)// 重写onCreateDialog方法
    {
        switch (id) {
            case DATE_DIALOG_ID:// 弹出日期选择对话框
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;// 为年份赋值
            mMonth = monthOfYear;// 为月份赋值
            mDay = dayOfMonth;// 为天赋值
            updateDisplay();// 显示设置的日期
        }
    };

    private void updateDisplay() {
        // 显示设置的时间
        txtTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }
}

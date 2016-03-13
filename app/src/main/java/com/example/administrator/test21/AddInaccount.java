package com.example.administrator.test21;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mingrisoft.dao.inaccountDAO;
import com.mingrisoft.model.Tb_inaccount;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/3/1.
 */
public class AddInaccount extends AppCompatActivity {

    private static final int DATE_DIALOG_ID =0;
    private EditText txtInMoney,txtInTime,txtInHandler,txtInMark;
    private Spinner spInType;
    private Button btnInSaveButton,btnInCancelButton;
    private int mYear,mMonth,mDay;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinaccount);
        Log.e("2", "2");
        txtInHandler=(EditText)findViewById(R.id.txtInHandler);
        txtInMark=(EditText)findViewById(R.id.txtInMark);
        txtInMoney=(EditText)findViewById(R.id.txtInMoney);
        txtInTime=(EditText)findViewById(R.id.txtInTime);
        spInType=(Spinner)findViewById(R.id.spInType);
        btnInCancelButton=(Button)findViewById(R.id.btnInCancel);
        btnInSaveButton=(Button)findViewById(R.id.btnInSave);

        btnInCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInMoney.setText("");
                txtInMoney.setHint("0.00");
                txtInTime.setText("");
                txtInTime.setHint("2011-01-01");
                txtInMark.setText("");
                txtInHandler.setText("");
                spInType.setSelection(0);

            }
        });
        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInMoney=txtInMoney.getText().toString();
                if(!strInMoney.isEmpty()){
                    inaccountDAO inaccountDAO=new inaccountDAO(AddInaccount.this);
                    Tb_inaccount tb_inaccount=new Tb_inaccount(inaccountDAO.getMaxId()+1,
                            Double.parseDouble(strInMoney),txtInTime.getText().toString(),
                            spInType.getSelectedItem().toString(),txtInHandler.getText().toString(),
                            txtInMark.getText().toString());
                    inaccountDAO.add(tb_inaccount);
                    Toast.makeText(AddInaccount.this,"【新增收入】已经添加成功！",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(AddInaccount.this,"请输入金额！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
    }

    private void updateDisplay() {
        txtInTime.setText(new StringBuffer().append(mYear).append("-").append(mMonth+1).append("-")
        .append(mDay));
    }


    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,mYear,mMonth,mDay);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear=year;
            mMonth=monthOfYear;
            mDay=dayOfMonth;
            updateDisplay();
        }
    };

}


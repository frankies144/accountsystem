package com.example.administrator.test21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mingrisoft.dao.inaccountDAO;
import com.mingrisoft.model.Tb_inaccount;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */

public class Inaccountinfo extends AppCompatActivity {
    public static final String FLAG="id";
    private ListView lvinfo;
    private String strType="";//记录管理类型

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfo);

        lvinfo=(ListView)findViewById(R.id.lvinaccountinfo);
        showInfo(R.id.btnininfo);

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf("|"));
                Intent intent = new Intent(Inaccountinfo.this, InfoManage.class);
                intent.putExtra(FLAG, new String[]{strid, strType});
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();// 实现基类中的方法
        showInfo(R.id.btnininfo);// 显示收入信息
    }
    private void showInfo(int intType){
        String[] strInfos=null;
        ArrayAdapter<String> arrayAdapter=null;
        strType="btnininfo";
        inaccountDAO inaccountinfo=new inaccountDAO(Inaccountinfo.this);
        List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0,(int)inaccountinfo.getCount());
        strInfos=new String[listinfos.size()];
        int m=0;
        for(Tb_inaccount tb_inaccount:listinfos){
            strInfos[m]=tb_inaccount.get_id()+"|"+tb_inaccount.getType()+" "+
                    String.valueOf(tb_inaccount.getMoney())+"元"+tb_inaccount.getTime();
            m++;
        }
        arrayAdapter=
        new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);

    }

}

package com.example.administrator.test21;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class Main extends AppCompatActivity {


    private GridView gvInfo;
    private String[] titles = new String[]{"新增支出", "新增收入", "我的支出", "我的收入", "数据管理", "系统设置", "收支便签", "退出"};
    private int[] images = new int[]{R.drawable.addoutaccount, R.drawable.addinaccount,
            R.drawable.outaccountinfo, R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
            R.drawable.accountflag, R.drawable.exit};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gvInfo = (GridView) findViewById(R.id.gvInfo);

        PictureAdapter adapter = new PictureAdapter(titles, images, this);
        gvInfo.setAdapter(adapter);
        Log.e("bb", "bb");
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(Main.this, AddOutaccount.class);// 使用AddOutaccount窗口初始化Intent
                        startActivity(intent);// 打开AddOutaccount
                        break;
                    case 1:
                        intent = new Intent(Main.this, AddInaccount.class);// 使用AddInaccount窗口初始化Intent
                        startActivity(intent);// 打开AddInaccount
                        break;
                    case 2:
                        intent = new Intent(Main.this, Outaccountinfo.class);// 使用Outaccountinfo窗口初始化Intent
                        startActivity(intent);// 打开Outaccountinfo
                        break;
                    case 3:
                        intent = new Intent(Main.this, Inaccountinfo.class);// 使用Inaccountinfo窗口初始化Intent
                        startActivity(intent);// 打开Inaccountinfo
                        break;
                    case 4:
                        intent = new Intent(Main.this, Showinfo.class);// 使用Showinfo窗口初始化Intent
                        startActivity(intent);// 打开Showinfo
                        break;
                    case 5:
                        intent = new Intent(Main.this, Sysset.class);// 使用Sysset窗口初始化Intent
                        startActivity(intent);// 打开Sysset
                        break;
                    case 6:
                        intent = new Intent(Main.this, Accountflag.class);// 使用Accountflag窗口初始化Intent
                        startActivity(intent);// 打开Accountflag
                        break;
                    case 7:
                        finish();// 关闭当前Activity
                }

            }

        });

    }
}

    //ViewHolder
    class ViewHolder{
        public TextView title;
        public ImageView image;

    }
    //Picture
    class Picture{
        private String title;
        private int imageId;

        public Picture() {
            super();
        }

        public Picture(String title, int imageId) {
            this.title = title;
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public int getImageId() {
            return imageId;
        }
    }
    //PictureAdapter
    class PictureAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private List<Picture> pictures;

        public PictureAdapter(String[] titles,int[] images,Context context) {
            super();
            pictures = new ArrayList<Picture>();
            inflater = LayoutInflater.from(context);
            for(int i=0;i<images.length;i++){
                Picture picture=new Picture(titles[i],images[i]);
                pictures.add(picture);
            }
        }
        @Override
        public int getCount() {
            if(null!=pictures){
                return pictures.size();
            }else{
            return 0;}
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView=inflater.inflate(R.layout.gvitem,null);
                viewHolder=new ViewHolder();
                viewHolder.title=(TextView)convertView.findViewById(R.id.ItemTitle);
                viewHolder.image=(ImageView)convertView.findViewById(R.id.ItemImage);
                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)convertView.getTag();
            }
            viewHolder.title.setText(pictures.get(position).getTitle());
            viewHolder.image.setImageResource(pictures.get(position).getImageId());

            return convertView;
        }
    }





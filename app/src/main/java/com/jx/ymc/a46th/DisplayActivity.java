package com.jx.ymc.a46th;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.jx.ymc.a46th.Global.equipment_used_on;

public class DisplayActivity extends AppCompatActivity {

    private TextView textview_equipment_type;
    private ListView listView;
    private com.github.clans.fab.FloatingActionButton flyNumberAdd;
    private int item_click = 0;
    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.display_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_display);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        textview_equipment_type = (TextView) findViewById(R.id.textView_equipment_type);
        if(Global.equipment_number.equals("")){
            toolbar.setTitle("编号为空");
        }else {
            toolbar.setTitle(Global.equipment_type_n);
            textview_equipment_type.setText(Global.equipment_type);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        display_adapter();


        dbHelper_display = new DBHelper(this, "info_46h.db", null, 1);
        display_database = dbHelper_display.getWritableDatabase();
        flyNumberAdd = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_change_display);
        flyNumberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items_flyNumber[] = {"1", "2", "3", "4", "5","6"};
                item_click = 1;
                final AlertDialog flyNumberChange_Dialog = new AlertDialog.Builder(DisplayActivity.this)
                        .setTitle("挂飞+")//设置对话框的标题
                        .setSingleChoiceItems(items_flyNumber, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item_click = which;
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                int num_fly = Integer.parseInt(Global.fly_count);
                                num_fly = num_fly + item_click+1;
                                Global.fly_count = String.valueOf(num_fly);
                                ContentValues values = new ContentValues();
                                values.put("fly_count",Global.fly_count);
                                String[] args = {String.valueOf(Global.equipment_number)};
                                display_database.update("armament" ,values , "equipment_number = ?" ,args);
                            }
                        }).create();
                flyNumberChange_Dialog.show();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void display_adapter(){
        final List<Map<String, Object>> data = new ArrayList<>();   // 数据源
        String[] data_arm = new String[]{Global.equipment_number, equipment_used_on,
                Global.airplane_number,Global.equipment_lifetime,
                Global.equipment_firstfixtime,Global.fixornot,Global.bigfix_time,Global.product_date,
                Global.used_count,Global.electric_conformity,Global.electric_checker,Global.electric_checkdate,
                Global.mechanical_conformity,Global.mechanical_checker,Global.mechanical_checkdate,Global.seal_conformity,
                Global.seal_checker,Global.seal_checkdate,Global.equipment_state,Global.in_or_out,Global.change_date,
                Global.change_people,Global.fly_count,Global.shuoming,Global.wulicanshu};
        String[] name = new String[] {"编号", "适用机型", "主机号", "规定寿命", "首翻期",
                "是否翻修", "翻修/大修时间", "出厂时间", "发射投放次数", "电气性能",
                "工作人", "检查时间", "机械性能", "工作人", "检查时间", "密封性能",
                "工作人", "检查时间", "设备状态", "借用状态", "出入库时间", "操作人","挂飞次数","故障描述","物理参数"};
        listView = (ListView)findViewById(R.id.listview_display);
        for (int i = 0; i < 25; i++) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("name", name[i]);
            temp.put("data_arm",data_arm[i]);
            data.add(temp);
        }
        final SimpleAdapter simpleAdapter = new SimpleAdapter(DisplayActivity.this, data, R.layout.display_item,
                new String[] {"name", "data_arm"}, new int[] {R.id.textView_display_1, R.id.textView_display_2});
        listView.setAdapter(simpleAdapter);
    }





}
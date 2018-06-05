package com.jx.ymc.a46th;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.jx.ymc.a46th.Global.equipment_used_on;

public class ArmamentActivity extends AppCompatActivity {


    private TextView textview_equipment_type;
    private EditText et;
    private TextWatcher textWatcher;
    private ListView listView;
    private com.github.clans.fab.FloatingActionButton flyNumberAdd;
    private int item_click = 0;
    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    String[] cunfang = new String[100];
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10,
            tv_11, tv_12, tv_13, tv_14, tv_15, tv_16, tv_17, tv_18, tv_19, tv_20,
            tv_21, tv_22, tv_23, tv_24, tv_25;
    private TextView ev_1, ev_2, ev_3, ev_4, ev_5, ev_6, ev_7, ev_8, ev_25;
    private EditText ev_9, ev_11, ev_12, ev_14, ev_15, ev_17, ev_18, ev_21, ev_22, ev_23, ev_24 ;
    private Spinner ev_10, ev_13, ev_16, ev_19, ev_20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.arm_xiugai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_xiugai);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        textview_equipment_type = (TextView) findViewById(R.id.textView_equipment_type_xiugai);
        toolbar.setTitle(Global.equipment_type_n);
        textview_equipment_type.setText(Global.equipment_type);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        tv_1 = (TextView) findViewById(R.id.textView_xiugai_1);
        tv_2 = (TextView) findViewById(R.id.textView_xiugai_2);
        tv_3 = (TextView) findViewById(R.id.textView_xiugai_3);
        tv_4 = (TextView) findViewById(R.id.textView_xiugai_4);
        tv_5 = (TextView) findViewById(R.id.textView_xiugai_5);
        tv_6 = (TextView) findViewById(R.id.textView_xiugai_6);
        tv_7 = (TextView) findViewById(R.id.textView_xiugai_7);
        tv_8 = (TextView) findViewById(R.id.textView_xiugai_8);
        tv_9 = (TextView) findViewById(R.id.textView_xiugai_9);
        tv_10 = (TextView) findViewById(R.id.textView_xiugai_10);
        tv_11 = (TextView) findViewById(R.id.textView_xiugai_11);
        tv_12 = (TextView) findViewById(R.id.textView_xiugai_12);
        tv_13 = (TextView) findViewById(R.id.textView_xiugai_13);
        tv_14 = (TextView) findViewById(R.id.textView_xiugai_14);
        tv_15 = (TextView) findViewById(R.id.textView_xiugai_15);
        tv_16 = (TextView) findViewById(R.id.textView_xiugai_16);
        tv_17 = (TextView) findViewById(R.id.textView_xiugai_17);
        tv_18 = (TextView) findViewById(R.id.textView_xiugai_18);
        tv_19 = (TextView) findViewById(R.id.textView_xiugai_19);
        tv_20 = (TextView) findViewById(R.id.textView_xiugai_20);
        tv_21 = (TextView) findViewById(R.id.textView_xiugai_21);
        tv_22 = (TextView) findViewById(R.id.textView_xiugai_22);
        tv_23 = (TextView) findViewById(R.id.textView_xiugai_23);
        tv_24 = (TextView) findViewById(R.id.textView_xiugai_24);
        tv_25 = (TextView) findViewById(R.id.textView_xiugai_25);
        ev_1 = (TextView) findViewById(R.id.editText_xiugai_1);
        ev_2 = (TextView) findViewById(R.id.editText_xiugai_2);
        ev_3 = (TextView) findViewById(R.id.editText_xiugai_3);
        ev_4 = (TextView) findViewById(R.id.editText_xiugai_4);
        ev_5 = (TextView) findViewById(R.id.editText_xiugai_5);
        ev_6 = (TextView) findViewById(R.id.editText_xiugai_6);
        ev_7 = (TextView) findViewById(R.id.editText_xiugai_7);
        ev_8 = (TextView) findViewById(R.id.editText_xiugai_8);
        ev_9 = (EditText) findViewById(R.id.editText_xiugai_9);
        ev_10 = (Spinner) findViewById(R.id.editText_xiugai_10);
        ev_11 = (EditText) findViewById(R.id.editText_xiugai_11);
        ev_12 = (EditText) findViewById(R.id.editText_xiugai_12);
        ev_13= (Spinner) findViewById(R.id.editText_xiugai_13);
        ev_14 = (EditText) findViewById(R.id.editText_xiugai_14);
        ev_15 = (EditText) findViewById(R.id.editText_xiugai_15);
        ev_16 = (Spinner) findViewById(R.id.editText_xiugai_16);
        ev_17 = (EditText) findViewById(R.id.editText_xiugai_17);
        ev_18 = (EditText) findViewById(R.id.editText_xiugai_18);
        ev_19 = (Spinner) findViewById(R.id.editText_xiugai_19);
        ev_20 = (Spinner) findViewById(R.id.editText_xiugai_20);
        ev_21 = (EditText) findViewById(R.id.editText_xiugai_21);
        ev_22 = (EditText) findViewById(R.id.editText_xiugai_22);
        ev_23 = (EditText) findViewById(R.id.editText_xiugai_23);
        ev_24 = (EditText) findViewById(R.id.editText_xiugai_24);
        ev_25 = (EditText) findViewById(R.id.editText_xiugai_25);

        tv_1.setText(name[0]);
        tv_2.setText(name[1]);
        tv_3.setText(name[2]);
        tv_4.setText(name[3]);
        tv_5.setText(name[4]);
        tv_6.setText(name[5]);
        tv_7.setText(name[6]);
        tv_8.setText(name[7]);
        tv_9.setText(name[8]);
        tv_10.setText(name[9]);
        tv_11.setText(name[10]);
        tv_12.setText(name[11]);
        tv_13.setText(name[12]);
        tv_14.setText(name[13]);
        tv_15.setText(name[14]);
        tv_16.setText(name[15]);
        tv_17.setText(name[16]);
        tv_18.setText(name[17]);
        tv_19.setText(name[18]);
        tv_20.setText(name[19]);
        tv_21.setText(name[20]);
        tv_22.setText(name[21]);
        tv_23.setText(name[22]);
        tv_24.setText(name[23]);
        tv_25.setText(name[24]);

        ev_1.setText(data_arm[0]);
        ev_2.setText(data_arm[1]);
        ev_3.setText(data_arm[2]);
        ev_4.setText(data_arm[3]);
        ev_5.setText(data_arm[4]);
        ev_6.setText(data_arm[5]);
        ev_7.setText(data_arm[6]);
        ev_8.setText(data_arm[7]);
        ev_9.setText(data_arm[8]);
        ev_11.setText(data_arm[10]);
        ev_12.setText(data_arm[11]);
        ev_14.setText(data_arm[13]);
        ev_15.setText(data_arm[14]);
        ev_17.setText(data_arm[16]);
        ev_18.setText(data_arm[17]);
        ev_21.setText(data_arm[20]);
        ev_22.setText(data_arm[21]);
        ev_23.setText(data_arm[22]);
        ev_24.setText(data_arm[23]);
        ev_25.setText(data_arm[24]);
        if(Global.electric_conformity.equals("合格")){
            ev_10.setSelection(0);
        }
        else if(Global.electric_conformity.equals("不合格")){
            ev_10.setSelection(1);
        }
        else if(Global.electric_conformity.equals("不涉及")){
            ev_10.setSelection(2);
        }
        if(Global.mechanical_conformity.equals("合格")){
            ev_13.setSelection(0);
        }
        else if(Global.mechanical_conformity.equals("不合格")){
            ev_13.setSelection(1);
        }
        else if(Global.mechanical_conformity.equals("不涉及")){
            ev_13.setSelection(2);
        }
        if(Global.seal_conformity.equals("合格")){
            ev_16.setSelection(0);
        }
        else if(Global.seal_conformity.equals("不合格")){
            ev_16.setSelection(1);
        }
        else if(Global.seal_conformity.equals("不涉及")){
            ev_16.setSelection(2);
        }
        if(Global.equipment_state.equals("存放")){
            ev_19.setSelection(0);
        }
        else if(Global.equipment_state.equals("挂机")){
            ev_19.setSelection(1);
        }
        else if(Global.equipment_state.equals("故障")){
            ev_19.setSelection(2);
        }
        else if(Global.equipment_state.equals("大修")){
            ev_19.setSelection(3);
        }
        else if(Global.equipment_state.equals("返航材")){
            ev_19.setSelection(4);
        }
        if(Global.in_or_out.equals("借用")){
            ev_20.setSelection(0);
        }
        else if(Global.in_or_out.equals("未借用")){
            ev_20.setSelection(1);
        }


        dbHelper_display = new DBHelper(this, "info_46h.db", null, 1);
        display_database = dbHelper_display.getWritableDatabase();
        flyNumberAdd = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_change_xiugai);
        flyNumberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Global.equipment_used_on = ev_2.getText().toString();//适用机型
                Global.airplane_number = ev_3.getText().toString();;//主机号
                Global.equipment_lifetime = ev_4.getText().toString();;//规定寿命
                Global.equipment_firstfixtime = ev_5.getText().toString();;//首翻期
                Global.fixornot = ev_6.getText().toString();;//是否翻修
                Global.bigfix_time = ev_7.getText().toString();;//翻修/大修时间
                Global.product_date = ev_8.getText().toString();;//出厂时间
                Global.used_count = ev_9.getText().toString();;//发射投放次数
                Global.electric_conformity = ev_10.getSelectedItem().toString();;//电气性能是否合格
                Global.electric_checker = ev_11.getText().toString();//电气性能检查工作人
                Global.electric_checkdate = ev_12.getText().toString();//电气性能检查时间
                Global.mechanical_conformity = ev_13.getSelectedItem().toString();//机械性能是否合格
                Global.mechanical_checker = ev_14.getText().toString();//机械性能检查工作人
                Global.mechanical_checkdate = ev_15.getText().toString();//机械性能检查时间
                Global.seal_conformity = ev_16.getSelectedItem().toString();//密封性能是否合格
                Global.seal_checker = ev_17.getText().toString();//密封性能检查工作人
                Global.seal_checkdate = ev_18.getText().toString();//密封性能检查时间
                Global.equipment_state = ev_19.getSelectedItem().toString();//设备状态
                Global.in_or_out = ev_20.getSelectedItem().toString();//借用状态
                Global.change_date = ev_21.getText().toString();//出入库时间
                Global.change_people = ev_22.getText().toString();//最后一次操作人
                Global.fly_count = ev_23.getText().toString();
                Global.shuoming = ev_24.getText().toString();
                Global.wulicanshu = ev_25.getText().toString();

                String[] args = {String.valueOf(Global.equipment_number)};
                ContentValues values = new ContentValues();
                values.put("equipment_used_on",Global.equipment_used_on);
                values.put("equipment_lifetime",Global.equipment_lifetime);
                values.put("equipment_firstfixtime",Global.equipment_firstfixtime);
                values.put("fixornot",Global.fixornot);
                values.put("bigfix_time",Global.bigfix_time);
                values.put("product_date",Global.product_date);
                values.put("used_count",Global.used_count);
                values.put("electric_conformity",Global.electric_conformity);
                values.put("electric_checker",Global.electric_checker);
                values.put("electric_checkdate",Global.electric_checkdate);
                values.put("mechanical_conformity",Global.mechanical_conformity);
                values.put("mechanical_checker",Global.mechanical_checker);
                values.put("mechanical_checkdate",Global.mechanical_checkdate);
                values.put("seal_conformity",Global.seal_conformity);
                values.put("seal_checker",Global.seal_checker);
                values.put("seal_checkdate",Global.seal_checkdate);
                values.put("airplane_number",Global.airplane_number);
                values.put("equipment_state",Global.equipment_state);
                values.put("in_or_out",Global.in_or_out);
                values.put("change_date",Global.change_date);
                values.put("change_people",Global.change_people);
                values.put("fly_count",Global.fly_count);
                values.put("shuoming",Global.shuoming);
                values.put("wulicanshu",Global.wulicanshu);
                display_database.update("armament" ,values , "equipment_number = ?" ,args);

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



}
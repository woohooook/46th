package com.jx.ymc.a46th;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ArmamentJ7aActivity extends AppCompatActivity {

    private TextView textView_1_2, textView_3_2;
    private Switch control_1_1, control_1_2, control_2, control_3_1, control_3_2, control_4;
    private LinearLayout ll_1_1, ll_1_2, ll_2, ll_3_1, ll_3_2, ll_4;
    private String[] sInfo_1 = new String[6];
    private String[] sInfo_2 = new String[6];
    private String[] sInfo_3 = new String[6];
    private Spinner spinner1_1, spinner1_2, spinner1_3,spinner1_4, spinner1_5, spinner1_6;
    private Spinner spinner2_1, spinner2_2, spinner2_3;
    private Spinner spinner3_1, spinner3_2, spinner3_3,spinner3_4, spinner3_5, spinner3_6;
    private Spinner spinner4_1, spinner4_2, spinner4_3;
    private boolean waiguazhuangtai_11, waiguazhuangtai_12;
    private boolean waiguazhuangtai_2;
    private boolean waiguazhuangtai_31, waiguazhuangtai_32;
    private boolean waiguazhuangtai_4;
    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private HashMap<String, String> map;
    private com.github.clans.fab.FloatingActionButton fab;
    String number_1_1 = "";
    String number_1_2 = "";
    String number_2 = "";
    String number_3_1 = "";
    String number_3_2 = "";
    String number_4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.arm_j7a);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_arm_j7a);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle(Global.numberOfAirplane);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_arm_j7a);

        waiguazhuangtai_11 =  false;
        waiguazhuangtai_12 =  false;
        waiguazhuangtai_2 =  false;
        waiguazhuangtai_31 =  false;
        waiguazhuangtai_32 =  false;
        waiguazhuangtai_4 =  false;

        if(Global.j7_point_1_1.equals("0")){
            waiguazhuangtai_11 = false;
        }
        else{
            waiguazhuangtai_11 = true;
        }
        if(Global.j7_point_1_2.equals("0")){
            waiguazhuangtai_12 = false;
        }
        else{
            waiguazhuangtai_12 = true;
        }
        if(Global.j7_point_2.equals("0")){
            waiguazhuangtai_2 = false;
        }
        else{
            waiguazhuangtai_2 = true;
        }
        if(Global.j7_point_3_1.equals("0")){
            waiguazhuangtai_31 = false;
        }
        else{
            waiguazhuangtai_31 = true;
        }
        if(Global.j7_point_3_2.equals("0")){
            waiguazhuangtai_32 = false;
        }
        else{
            waiguazhuangtai_32 = true;
        }
        if(Global.j7_point_p.equals("0")){
            waiguazhuangtai_4 = false;
        }
        else{
            waiguazhuangtai_4 = true;
        }

        spinner1_1 = (Spinner) findViewById(R.id.spinner_j7a_1_1);
        spinner1_2 = (Spinner) findViewById(R.id.spinner_j7a_1_2);
        spinner1_3 = (Spinner) findViewById(R.id.spinner_j7a_1_3);
        spinner1_4 = (Spinner) findViewById(R.id.spinner_j7a_1_4);
        spinner1_5 = (Spinner) findViewById(R.id.spinner_j7a_1_5);
        spinner1_6 = (Spinner) findViewById(R.id.spinner_j7a_1_6);
        textView_1_2 = (TextView) findViewById(R.id.textView_j7a_1_2);
        control_1_1 = (Switch) findViewById(R.id.control_switch_j7a_1_1);
        control_1_2 = (Switch) findViewById(R.id.control_switch_j7a_1_2);
        ll_1_1 = (LinearLayout) findViewById(R.id.ll_j7a_1_1);
        ll_1_2 = (LinearLayout) findViewById(R.id.ll_j7a_1_2);
        loadLeixing1_1();
        loadXinghao1_1();
        loadLeixing1_2();
        if(waiguazhuangtai_11){
            control_1_1.setChecked(true);
            ll_1_1.setVisibility(View.VISIBLE);
            control_1_2.setVisibility(View.VISIBLE);
            textView_1_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_12){
            control_1_2.setVisibility(View.VISIBLE);
            textView_1_2.setVisibility(View.VISIBLE);
            control_1_2.setChecked(true);
            ll_1_2.setVisibility(View.VISIBLE);
        }
        if(!waiguazhuangtai_12){
            control_1_2.setChecked(false);
        }
        if(waiguazhuangtai_11){
            spinner1_1.setSelection(1);
        }
        spinner1_1.setOnItemSelectedListener(new Spinner1ClickListener1());
        spinner1_2.setOnItemSelectedListener(new Spinner1ClickListener2());
        if(waiguazhuangtai_12){
            spinner1_4.setSelection(1);
        }
        spinner1_4.setOnItemSelectedListener(new Spinner1ClickListener3());
        spinner1_5.setOnItemSelectedListener(new Spinner1ClickListener4());
        control_1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_1_1.setVisibility(View.VISIBLE);
                    textView_1_2.setVisibility(View.VISIBLE);
                    control_1_2.setVisibility(View.VISIBLE);
                    if(waiguazhuangtai_12){
                        ll_1_2.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    ll_1_1.setVisibility(View.INVISIBLE);
                    textView_1_2.setVisibility(View.INVISIBLE);
                    control_1_2.setChecked(false);
                    control_1_2.setVisibility(View.INVISIBLE);
                    ll_1_2.setVisibility(View.INVISIBLE);
                }
            }
        });

        control_1_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_1_2.setVisibility(View.VISIBLE);
                }
                else{
                    ll_1_2.setVisibility(View.INVISIBLE);
                }
            }
        });


        spinner2_1 = (Spinner) findViewById(R.id.spinner_j7a_2_1);
        spinner2_2 = (Spinner) findViewById(R.id.spinner_j7a_2_2);
        spinner2_3 = (Spinner) findViewById(R.id.spinner_j7a_2_3);
        ll_2 = (LinearLayout) findViewById(R.id.ll_j7a_2);
        control_2 = (Switch) findViewById(R.id.control_switch_j7a_2);
        loadLeixing2();
        loadXinghao2();
        if(waiguazhuangtai_2){
            control_2.setChecked(true);
            ll_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_2){
            spinner2_1.setSelection(1);
        }
        spinner2_1.setOnItemSelectedListener(new Spinner2ClickListener1());
        spinner2_2.setOnItemSelectedListener(new Spinner2ClickListener2());

        control_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_2.setVisibility(View.VISIBLE);
                }
                else{
                    ll_2.setVisibility(View.INVISIBLE);
                }
            }
        });

        spinner3_1 = (Spinner) findViewById(R.id.spinner_j7a_3_1);
        spinner3_2 = (Spinner) findViewById(R.id.spinner_j7a_3_2);
        spinner3_3 = (Spinner) findViewById(R.id.spinner_j7a_3_3);
        spinner3_4 = (Spinner) findViewById(R.id.spinner_j7a_3_4);
        spinner3_5 = (Spinner) findViewById(R.id.spinner_j7a_3_5);
        spinner3_6 = (Spinner) findViewById(R.id.spinner_j7a_3_6);
        textView_3_2 = (TextView) findViewById(R.id.textView_j7a_3_2);
        control_3_1 = (Switch) findViewById(R.id.control_switch_j7a_3_1);
        control_3_2 = (Switch) findViewById(R.id.control_switch_j7a_3_2);
        ll_3_1 = (LinearLayout) findViewById(R.id.ll_j7a_3_1);
        ll_3_2 = (LinearLayout) findViewById(R.id.ll_j7a_3_2);
        loadLeixing3_1();
        loadXinghao3_1();
        loadLeixing3_2();
        if(waiguazhuangtai_31){
            control_3_1.setChecked(true);
            ll_3_1.setVisibility(View.VISIBLE);
            control_3_2.setVisibility(View.VISIBLE);
            textView_3_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_32){
            control_3_2.setVisibility(View.VISIBLE);
            textView_3_2.setVisibility(View.VISIBLE);
            control_3_2.setChecked(true);
            ll_3_2.setVisibility(View.VISIBLE);
        }
        if(!waiguazhuangtai_32){
            control_3_2.setChecked(false);
        }
        if(waiguazhuangtai_31){
            spinner3_1.setSelection(1);
        }
        spinner3_1.setOnItemSelectedListener(new Spinner3ClickListener1());
        spinner3_2.setOnItemSelectedListener(new Spinner3ClickListener2());
        if(waiguazhuangtai_32){
            spinner3_4.setSelection(1);
        }
        spinner3_4.setOnItemSelectedListener(new Spinner3ClickListener3());
        spinner3_5.setOnItemSelectedListener(new Spinner3ClickListener4());
        control_3_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_3_1.setVisibility(View.VISIBLE);
                    textView_3_2.setVisibility(View.VISIBLE);
                    control_3_2.setVisibility(View.VISIBLE);
                    if(waiguazhuangtai_32){
                        ll_3_2.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    ll_3_1.setVisibility(View.INVISIBLE);
                    textView_3_2.setVisibility(View.INVISIBLE);
                    control_3_2.setChecked(false);
                    control_3_2.setVisibility(View.INVISIBLE);
                    ll_3_2.setVisibility(View.INVISIBLE);
                }
            }
        });

        control_3_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_3_2.setVisibility(View.VISIBLE);
                }
                else{
                    ll_3_2.setVisibility(View.INVISIBLE);
                }
            }
        });

        spinner4_1 = (Spinner) findViewById(R.id.spinner_j7a_4_1);
        spinner4_2 = (Spinner) findViewById(R.id.spinner_j7a_4_2);
        spinner4_3 = (Spinner) findViewById(R.id.spinner_j7a_4_3);
        ll_4 = (LinearLayout) findViewById(R.id.ll_j7a_4);
        control_4 = (Switch) findViewById(R.id.control_switch_j7a_4);
        loadLeixing4();
        loadXinghao4();
        if(waiguazhuangtai_4){
            control_4.setChecked(true);
            ll_4.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_4){
            spinner4_1.setSelection(1);
        }
        spinner4_1.setOnItemSelectedListener(new Spinner4ClickListener1());
        spinner4_2.setOnItemSelectedListener(new Spinner4ClickListener2());

        control_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_4.setVisibility(View.VISIBLE);
                }
                else{
                    ll_4.setVisibility(View.INVISIBLE);
                }
            }
        });

        spinner1_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_1_1 = spinner1_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1_6.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_1_2 = spinner1_6.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_2 = spinner2_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3_6.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_3_2 = spinner3_6.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_3_1 = spinner3_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_4 = spinner4_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String point_1_1 = "0";
                if(control_1_1.isChecked()){
                    point_1_1 = "1";
                }
                String point_1_2 = "0";
                if(control_1_2.isChecked()){
                    point_1_2 = "1";
                }
                String point_2 = "0";
                if(control_2.isChecked()){
                    point_2 = "1";
                }
                String point_3_1 = "0";
                if(control_3_1.isChecked()){
                    point_3_1 = "1";
                }
                String point_3_2 = "0";
                if(control_3_2.isChecked()){
                    point_3_2 = "1";
                }
                String point_4 = "0";
                if(control_4.isChecked()){
                    point_4 = "1";
                }


                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getWritableDatabase();
                String[] args = {String.valueOf(Global.numberOfAirplane)};
                ContentValues values = new ContentValues();
                values.put("point_1_1",point_1_1);
                values.put("point_1_2",point_1_2);
                values.put("point_2",point_2);
                values.put("point_3_1",point_3_1);
                values.put("point_3_2",point_3_2);
                values.put("point_p",point_4);
                values.put("number_1_1",number_1_1);
                values.put("number_1_2",number_1_2);
                values.put("number_2",number_2);
                values.put("number_3_1",number_3_1);
                values.put("number_3_2",number_3_2);
                values.put("number_p",number_4);
                display_database.update("j7a" ,values , "j7a_number = ?" ,args);
                Toast.makeText(ArmamentJ7aActivity.this, "更新成功！", Toast.LENGTH_LONG).show();
            }
        });


    }


    public void loadLeixing1_1() {
        String[] array1 = new String[]{"请选择", "内侧挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner1_1.setAdapter(adapterOne);
    }

    public void loadXinghao1_1() {
        String[] array2 = new String[]{"请选择", "7LN"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner1_2.setAdapter(modelTwo);
    }
    public void loadLeixing1_2() {
        String[] array1 = new String[]{"请选择", "导发架", "火发器"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner1_4.setAdapter(adapterOne);
    }

    public class Spinner1ClickListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner1_2.setVisibility(View.INVISIBLE);
                spinner1_3.setVisibility(View.INVISIBLE);
            } else {
                spinner1_2.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner1_2.setSelection(0);
                if(waiguazhuangtai_11){
                    spinner1_2.setSelection(1);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner1ClickListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner1_3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner1_3.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getReadableDatabase();
                String[] args = {String.valueOf(str)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_type_n = ?", args);
                String[] array3 = new String[100];
                int number_set_count = 1;
                array3[0] = "请选择";
                if (cursor.moveToFirst()) {
                    do {
                        String numberForSet = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        array3[number_set_count] = numberForSet;
                        number_set_count++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String[] array4 = new String[number_set_count];
                int useToCopy = 0;
                for(useToCopy = 0;useToCopy<number_set_count;useToCopy++){
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentJ7aActivity.this, array4, R.layout.spinner_item);
                spinner1_3.setAdapter(modelThree);
                if(waiguazhuangtai_11){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j7_number_1_1))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner1_3.setSelection(numberForSpinner3);
                }
                sInfo_2[1] = spinner1_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner1ClickListener3 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner1_5.setVisibility(View.INVISIBLE);
                spinner1_6.setVisibility(View.INVISIBLE);
            } else if (str.equals("导发架")){
                String[] array2 = new String[]{"请选择",  "PF-7E"};
                SpinnerAdapter modelTwo = new SpinnerAdapter(ArmamentJ7aActivity.this, array2, R.layout.spinner_item);
                spinner1_5.setAdapter(modelTwo);
                spinner1_5.setVisibility(View.VISIBLE);
                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner1_5.setSelection(0);
                if(waiguazhuangtai_12){
                    spinner1_5.setSelection(1);
                }
            } else if (str.equals("火发器")){
                String[] array2 = new String[]{"请选择",  "HF-7C"};
                SpinnerAdapter modelTwo = new SpinnerAdapter(ArmamentJ7aActivity.this, array2, R.layout.spinner_item);
                spinner1_5.setAdapter(modelTwo);
                spinner1_5.setVisibility(View.VISIBLE);
                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner1_5.setSelection(0);
                if(waiguazhuangtai_12){
                    spinner1_5.setSelection(1);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner1ClickListener4 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner1_6.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner1_6.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getReadableDatabase();
                String[] args = {String.valueOf(str)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_type_n = ?", args);
                String[] array3 = new String[100];
                int number_set_count = 1;
                array3[0] = "请选择";
                if (cursor.moveToFirst()) {
                    do {
                        String numberForSet = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        array3[number_set_count] = numberForSet;
                        number_set_count++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String[] array4 = new String[number_set_count];
                int useToCopy = 0;
                for(useToCopy = 0;useToCopy<number_set_count;useToCopy++){
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentJ7aActivity.this, array4, R.layout.spinner_item);
                spinner1_6.setAdapter(modelThree);
                if(waiguazhuangtai_12){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j7_number_1_2))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner1_6.setSelection(numberForSpinner3);
                }
                sInfo_3[1] = spinner1_6.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void loadLeixing2() {
        String[] array1 = new String[]{"请选择", "机身挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner2_1.setAdapter(adapterOne);
    }

    public void loadXinghao2() {
        String[] array2 = new String[]{"请选择", "J7II-6115-0"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner2_2.setAdapter(modelTwo);
    }
    public class Spinner2ClickListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner2_2.setVisibility(View.INVISIBLE);
                spinner2_3.setVisibility(View.INVISIBLE);
            } else {
                spinner2_2.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner2_2.setSelection(0);
                if(waiguazhuangtai_2) {
                    dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                    display_database = dbHelper_display.getReadableDatabase();
                    String[] args = {String.valueOf(Global.j7_number_2)};
                    String dataOfType = "";
                    Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args);
                    if (cursor.moveToFirst()) {
                        dataOfType = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                    }
                    cursor.close();
                    if (dataOfType.equals("GDJ-IV9")) {
                        spinner2_2.setSelection(1);
                    } else if (dataOfType.equals("GDJ-IV1B")) {
                        spinner2_2.setSelection(2);
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner2ClickListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner2_3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner2_3.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getReadableDatabase();
                String[] args = {String.valueOf(str)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_type_n = ?", args);
                String[] array3 = new String[100];
                int number_set_count = 1;
                array3[0] = "请选择";
                if (cursor.moveToFirst()) {
                    do {
                        String numberForSet = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        array3[number_set_count] = numberForSet;
                        number_set_count++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String[] array4 = new String[number_set_count];
                int useToCopy = 0;
                for(useToCopy = 0;useToCopy<number_set_count;useToCopy++){
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentJ7aActivity.this, array4, R.layout.spinner_item);
                spinner2_3.setAdapter(modelThree);
                if(waiguazhuangtai_2){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j7_number_2))
                            numberForSpinner3 = numberToInit;
                    }
                    spinner2_3.setSelection(numberForSpinner3);
                }
                sInfo_2[3] = spinner2_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void loadLeixing3_1() {
        String[] array1 = new String[]{"请选择", "内侧挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner3_1.setAdapter(adapterOne);
    }

    public void loadXinghao3_1() {
        String[] array2 = new String[]{"请选择", "7LN"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner3_2.setAdapter(modelTwo);
    }
    public void loadLeixing3_2() {
        String[] array1 = new String[]{"请选择", "导发架", "火发器"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner3_4.setAdapter(adapterOne);
    }

    public class Spinner3ClickListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner3_2.setVisibility(View.INVISIBLE);
                spinner3_3.setVisibility(View.INVISIBLE);
            } else {
                spinner3_2.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner3_2.setSelection(0);
                if(waiguazhuangtai_31){
                    spinner3_2.setSelection(1);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner3ClickListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner3_3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner3_3.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getReadableDatabase();
                String[] args = {String.valueOf(str)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_type_n = ?", args);
                String[] array3 = new String[100];
                int number_set_count = 1;
                array3[0] = "请选择";
                if (cursor.moveToFirst()) {
                    do {
                        String numberForSet = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        array3[number_set_count] = numberForSet;
                        number_set_count++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String[] array4 = new String[number_set_count];
                int useToCopy = 0;
                for(useToCopy = 0;useToCopy<number_set_count;useToCopy++){
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentJ7aActivity.this, array4, R.layout.spinner_item);
                spinner3_3.setAdapter(modelThree);
                if(waiguazhuangtai_31){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j7_number_3_1))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner3_3.setSelection(numberForSpinner3);
                }
                sInfo_2[1] = spinner3_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner3ClickListener3 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner3_5.setVisibility(View.INVISIBLE);
                spinner3_6.setVisibility(View.INVISIBLE);
            } else if (str.equals("导发架")){
                String[] array2 = new String[]{"请选择",  "PF-7E"};
                SpinnerAdapter modelTwo = new SpinnerAdapter(ArmamentJ7aActivity.this, array2, R.layout.spinner_item);
                spinner3_5.setAdapter(modelTwo);
                spinner3_5.setVisibility(View.VISIBLE);
                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner3_5.setSelection(0);
                if(waiguazhuangtai_32){
                    spinner3_5.setSelection(1);
                }
            } else if (str.equals("火发器")){
                String[] array2 = new String[]{"请选择",  "HF-7C"};
                SpinnerAdapter modelTwo = new SpinnerAdapter(ArmamentJ7aActivity.this, array2, R.layout.spinner_item);
                spinner3_5.setAdapter(modelTwo);
                spinner3_5.setVisibility(View.VISIBLE);
                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner3_5.setSelection(0);
                if(waiguazhuangtai_32){
                    spinner3_5.setSelection(1);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner3ClickListener4 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner3_6.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner3_6.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getReadableDatabase();
                String[] args = {String.valueOf(str)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_type_n = ?", args);
                String[] array3 = new String[100];
                int number_set_count = 1;
                array3[0] = "请选择";
                if (cursor.moveToFirst()) {
                    do {
                        String numberForSet = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        array3[number_set_count] = numberForSet;
                        number_set_count++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String[] array4 = new String[number_set_count];
                int useToCopy = 0;
                for(useToCopy = 0;useToCopy<number_set_count;useToCopy++){
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentJ7aActivity.this, array4, R.layout.spinner_item);
                spinner3_6.setAdapter(modelThree);
                if(waiguazhuangtai_32){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j7_number_1_2))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner3_6.setSelection(numberForSpinner3);
                }
                sInfo_3[1] = spinner3_6.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    public void loadLeixing4() {
        String[] array1 = new String[]{"请选择", "航炮"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner4_1.setAdapter(adapterOne);
    }

    public void loadXinghao4() {
        String[] array2 = new String[]{"请选择", "23-3"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner4_2.setAdapter(modelTwo);
    }
    public class Spinner4ClickListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner4_2.setVisibility(View.INVISIBLE);
                spinner4_3.setVisibility(View.INVISIBLE);
            } else {
                spinner4_2.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner4_2.setSelection(0);
                if(waiguazhuangtai_4) {
                        spinner4_2.setSelection(1);
                    }
                }
            }


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner4ClickListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner4_3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner4_3.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentJ7aActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getReadableDatabase();
                String[] args = {String.valueOf(str)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_type_n = ?", args);
                String[] array3 = new String[100];
                int number_set_count = 1;
                array3[0] = "请选择";
                if (cursor.moveToFirst()) {
                    do {
                        String numberForSet = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        array3[number_set_count] = numberForSet;
                        number_set_count++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                String[] array4 = new String[number_set_count];
                int useToCopy = 0;
                for(useToCopy = 0;useToCopy<number_set_count;useToCopy++){
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentJ7aActivity.this, array4, R.layout.spinner_item);
                spinner4_3.setAdapter(modelThree);
                if(waiguazhuangtai_4){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j7_number_p))
                            numberForSpinner3 = numberToInit;
                    }
                    spinner4_3.setSelection(numberForSpinner3);
                }
                sInfo_2[3] = spinner4_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
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
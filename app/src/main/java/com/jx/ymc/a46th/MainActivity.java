package com.jx.ymc.a46th;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.solver.Goal;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionMenu fab;
    private Toolbar toolbar;
    private com.github.clans.fab.FloatingActionButton fab_1, fab_2, fab_3, fab_8f, fab_7a;
    private RelativeLayout rl_1, rl_2, rl_3, rl_4;
    private DrawerLayout dl;
    private ImageButton bt1 ,bt2 ,bt3 ,bt4, bt5, bt6, bt7, bt8;
    private Button bt_c1, bt_c2, bt_c3, bt_c4, bt_c5, bt_c6, bt_c7, bt_c8, bt_c9;
    private Boolean point1_j8f = false;
    private Boolean point2_j8f = false;
    private Boolean point3_j8f = false;
    private Boolean point4_j8f = false;
    private Boolean point5_j8f = false;
    private Boolean point1_j7a = false;
    private Boolean point2_j7a = false;
    private Boolean point3_j7a = false;
    private Boolean pointp_j7a = false;
    private View view_1;
    private boolean xinghao_zhankai = false;
    private TextView textView_1, textView_2;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private String item_click_j8f,item_click_j7a;
    private HashMap<String, String> map;
    private int which_chosed = 0;
    String[] numberOf_j8 = new String[6];
    String[] numberOf_j7 = new String[5];
    String[] j8fNumber = new String[100];
    String[] j8fType = new String[100];
    String[] j8fPoint1 = new String[100];
    String[] j8fNumber1 = new String[100];
    String[] j8fPoint2 = new String[100];
    String[] j8fNumber2 = new String[100];
    String[] j8fPoint3 = new String[100];
    String[] j8fNumber3 = new String[100];
    String[] j8fPoint4 = new String[100];
    String[] j8fNumber4 = new String[100];
    String[] j8fPoint5 = new String[100];
    String[] j8fNumber5 = new String[100];
    String[] j7aNumber = new String[100];
    String[] j7aType = new String[100];
    String[] j7aPoint1 = new String[100];
    String[] j7aNumber1 = new String[100];
    String[] j7aPoint2 = new String[100];
    String[] j7aNumber2 = new String[100];
    String[] j7aPoint3 = new String[100];
    String[] j7aNumber3 = new String[100];
    String[] j7aPoint4 = new String[100];
    String[] j7aNumber4 = new String[100];
    String[] j7aPoint5 = new String[100];
    String[] j7aNumber5 = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle("J8-F");
        setSupportActionBar(toolbar);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
        //    actionBar.hide();
        //}

        buttonside();//对应按钮位置设置
        fab_typechange();//机型切换按钮的实现以及隐藏显示
        people_init();//人员信息初始化
        j8f_init();//j8f信息初始化
        j7a_init();//j7a信息初始化
        fab_numberchange();//号码切换按钮的实现
        fab_setting();//外挂维护按钮的实现

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //外挂点按钮位置设置
    public void buttonside(){

        rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
        rl_2 = (RelativeLayout) findViewById(R.id.rl_2);
        rl_3 = (RelativeLayout) findViewById(R.id.rl_3);
        rl_4 = (RelativeLayout) findViewById(R.id.rl_4);
        //各按钮表示外挂可用状态，X表示不存在
        bt1 = (ImageButton)findViewById(R.id.imageButton1);
        bt2 = (ImageButton)findViewById(R.id.imageButton2);
        bt3 = (ImageButton)findViewById(R.id.imageButton3);
        bt4 = (ImageButton)findViewById(R.id.imageButton4);
        bt5 = (ImageButton)findViewById(R.id.imageButton5);
        bt6 = (ImageButton)findViewById(R.id.imageButton6);
        bt7 = (ImageButton)findViewById(R.id.imageButton7);
        bt8 = (ImageButton)findViewById(R.id.imageButton8);

        Display display = this.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        //设置各按钮位置(非绝对值)
        bt1.setX((float) (width*0.80));
        bt1.setY((float) (height*0.40));
        bt2.setX((float) (width*0.715));
        bt2.setY((float) (height*0.30));
        bt3.setX((float) (width*0.445));
        bt3.setY((float) (height*0.2));
        bt4.setX((float) (width*0.2));
        bt4.setY((float) (height*0.31));
        bt5.setX((float) (width*0.12));
        bt5.setY((float) (height*0.41));

        bt6.setX((float) (width*0.74));
        bt6.setY((float) (height*0.20));
        bt7.setX((float) (width*0.445));
        bt7.setY((float) (height*0.1));
        bt8.setX((float) (width*0.15));
        bt8.setY((float) (height*0.20));

        bt_c1 = (Button)findViewById(R.id.Button_click_1);
        bt_c2 = (Button)findViewById(R.id.Button_click_2);
        bt_c3 = (Button)findViewById(R.id.Button_click_3);
        bt_c4 = (Button)findViewById(R.id.Button_click_4);
        bt_c5 = (Button)findViewById(R.id.Button_click_5);
        bt_c6 = (Button)findViewById(R.id.Button_click_6);
        bt_c7 = (Button)findViewById(R.id.Button_click_7);
        bt_c8 = (Button)findViewById(R.id.Button_click_8);
        bt_c9 = (Button)findViewById(R.id.Button_click_9);

        //各按钮的点击事件，展示外挂物信息
        bt_c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 1;
                transfrom();
                if(point1_j8f){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 2;
                transfrom();
                if(point2_j8f){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 3;
                transfrom();
                if(point3_j8f){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 4;
                transfrom();
                if(point4_j8f){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 5;
                transfrom();
                if(point5_j8f){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 1;
                transfrom();
                if(point1_j7a){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 2;
                transfrom();
                if(point2_j7a){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 3;
                transfrom();
                if(point3_j7a){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });
        bt_c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_chosed = 4;
                transfrom();
                if(pointp_j7a){
                    Intent intent_display = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent_display);
                }
            }
        });

    }

    //外挂状态更改维护
    public void fab_setting(){
        fab_3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_waigua);
        fab_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.numberOfAirplane.equals("")){
                    Toast.makeText(MainActivity.this, "请选择机号！", Toast.LENGTH_LONG).show();
                }
                else {
                    if (Global.j8f) {
                        Intent intent_armsetting = new Intent(MainActivity.this, ArmamentSettingActivity.class);
                        startActivity(intent_armsetting);
                    }
                    else if(Global.j7a){
                        Intent intent_armsetting = new Intent(MainActivity.this, ArmamentJ7aActivity.class);
                        startActivity(intent_armsetting);
                    }
                }
            }
        });
    }

    //机型切换
    public void fab_typechange(){
        textView_2 = (TextView) findViewById(R.id.textView_2);
        fab = (FloatingActionMenu) findViewById(R.id.fab);
        fab_1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_xinghao);
        fab_8f = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_8f);
        fab_7a = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_7a);
        fab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!xinghao_zhankai) {
                    fab_8f.setVisibility(View.VISIBLE);
                    fab_7a.setVisibility(View.VISIBLE);
                    xinghao_zhankai = true;
                    fab_1.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                else if(xinghao_zhankai){
                    fab_8f.setVisibility(View.GONE);
                    fab_7a.setVisibility(View.GONE);
                    xinghao_zhankai = false;
                    fab_1.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
            }
        });
        fab_8f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                bt4.setVisibility(View.GONE);
                bt5.setVisibility(View.GONE);
                bt6.setVisibility(View.GONE);
                bt7.setVisibility(View.GONE);
                bt8.setVisibility(View.GONE);
                bt_c1.setBackgroundResource(R.drawable.button_color_change);
                bt_c2.setBackgroundResource(R.drawable.button_color_change);
                bt_c3.setBackgroundResource(R.drawable.button_color_change);
                bt_c4.setBackgroundResource(R.drawable.button_color_change);
                bt_c5.setBackgroundResource(R.drawable.button_color_change);
                bt_c6.setBackgroundResource(R.drawable.button_color_change);
                bt_c7.setBackgroundResource(R.drawable.button_color_change);
                bt_c8.setBackgroundResource(R.drawable.button_color_change);
                bt_c9.setBackgroundResource(R.drawable.button_color_change);
                if(Global.j8f == false){
                    Global.j8f = true;
                    Global.j7a = false;
                    fab_8f.setImageResource(R.drawable.ic_airplanemode_active_black_24dp);
                    fab_7a.setImageResource(R.drawable.ic_airplanemode_inactive_black_24dp);
                    rl_1.setVisibility(View.VISIBLE);
                    rl_2.setVisibility(View.GONE);
                    rl_3.setVisibility(View.VISIBLE);
                    rl_4.setVisibility(View.GONE);
                    Resources resources = getResources();
                    Drawable drawable = resources.getDrawable(R.mipmap.ic_j8_wg);
                    dl.setBackground(drawable);
                    toolbar.setTitle("J8-F");
                    setSupportActionBar(toolbar);
                    textView_2.setText("请选择");
                }
                else if(Global.j8f == true){
                    Global.j7a = true;
                    Global.j8f = false;
                    fab_7a.setImageResource(R.drawable.ic_airplanemode_active_black_24dp);
                    fab_8f.setImageResource(R.drawable.ic_airplanemode_inactive_black_24dp);
                    rl_1.setVisibility(View.GONE);
                    rl_2.setVisibility(View.VISIBLE);
                    rl_3.setVisibility(View.GONE);
                    rl_4.setVisibility(View.VISIBLE);
                    Resources resources = getResources();
                    Drawable drawable = resources.getDrawable(R.mipmap.ic_j7_wg);
                    dl.setBackground(drawable);
                    toolbar.setTitle("J7-A");
                    setSupportActionBar(toolbar);
                    textView_2.setText("请选择");
                }
            }
        });
        fab_7a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                bt4.setVisibility(View.GONE);
                bt5.setVisibility(View.GONE);
                bt6.setVisibility(View.GONE);
                bt7.setVisibility(View.GONE);
                bt8.setVisibility(View.GONE);
                bt_c1.setBackgroundResource(R.drawable.button_color_change);
                bt_c2.setBackgroundResource(R.drawable.button_color_change);
                bt_c3.setBackgroundResource(R.drawable.button_color_change);
                bt_c4.setBackgroundResource(R.drawable.button_color_change);
                bt_c5.setBackgroundResource(R.drawable.button_color_change);
                bt_c6.setBackgroundResource(R.drawable.button_color_change);
                bt_c7.setBackgroundResource(R.drawable.button_color_change);
                bt_c8.setBackgroundResource(R.drawable.button_color_change);
                bt_c9.setBackgroundResource(R.drawable.button_color_change);
                if(Global.j7a == false){
                    Global.j7a = true;
                    Global.j8f = false;
                    fab_7a.setImageResource(R.drawable.ic_airplanemode_active_black_24dp);
                    fab_8f.setImageResource(R.drawable.ic_airplanemode_inactive_black_24dp);
                    rl_1.setVisibility(View.GONE);
                    rl_2.setVisibility(View.VISIBLE);
                    rl_3.setVisibility(View.GONE);
                    rl_4.setVisibility(View.VISIBLE);
                    Resources resources = getResources();
                    Drawable drawable = resources.getDrawable(R.mipmap.ic_j7_wg);
                    dl.setBackground(drawable);
                    toolbar.setTitle("J7-A");
                    setSupportActionBar(toolbar);
                    textView_2.setText("请选择");

                }
                else if(Global.j7a == true){
                    Global.j8f = true;
                    Global.j7a = false;
                    fab_8f.setImageResource(R.drawable.ic_airplanemode_active_black_24dp);
                    fab_7a.setImageResource(R.drawable.ic_airplanemode_inactive_black_24dp);
                    rl_1.setVisibility(View.VISIBLE);
                    rl_2.setVisibility(View.GONE);
                    rl_3.setVisibility(View.VISIBLE);
                    rl_4.setVisibility(View.GONE);
                    Resources resources = getResources();
                    Drawable drawable = resources.getDrawable(R.mipmap.ic_j8_wg);
                    dl.setBackground(drawable);
                    toolbar.setTitle("J8-F");
                    setSupportActionBar(toolbar);
                    textView_2.setText("请选择");
                }
            }
        });
    }

    public void fab_numberchange(){
        dbHelper = new DBHelper(this, "info_46h.db", null, 1);//创建数据库文件
        db = dbHelper.getWritableDatabase();//打开数据库文件并传递返回值给db

        fab_2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_jihao);
        fab_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items_j8f[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "19", "20", "21", "22", "23", "24"};
                final String items_j7a[] = {"31", "32", "33", "34"};
                item_click_j8f = "01";
                item_click_j7a = "31";
                point1_j8f = false;
                point2_j8f = false;
                point3_j8f = false;
                point4_j8f = false;
                point5_j8f = false;
                point1_j7a = false;
                point2_j7a = false;
                point3_j7a = false;
                pointp_j7a = false;
                final AlertDialog dialog_num_j8f = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("J8-F机号")//设置对话框的标题
                        .setSingleChoiceItems(items_j8f, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item_click_j8f = items_j8f[which];
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
                                textView_2.setText(item_click_j8f);
                                Global.numberOfAirplane = item_click_j8f;
                                String[] args = {String.valueOf(item_click_j8f)};
                                //获取对应机号的外挂状态，用于切换图片或者按钮的显示
                                Cursor cursor_j8f_numberchange = db.rawQuery("select * from jc8f where  jc8f_number = ?",args);
                                if(cursor_j8f_numberchange.moveToFirst()) {
                                    Global.j8_point_1_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_1_1"));
                                    Global.j8_point_2_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_2_1"));
                                    Global.j8_point_3 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_3"));
                                    Global.j8_point_4_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_4_1"));
                                    Global.j8_point_5_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_5_1"));
                                    Global.j8_point_1_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_1_2"));
                                    Global.j8_point_2_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_2_2"));
                                    Global.j8_point_4_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_4_2"));
                                    Global.j8_point_5_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("point_5_2"));
                                    Global.j8_number_1_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_1_1"));
                                    Global.j8_number_2_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_2_1"));
                                    Global.j8_number_3 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_3"));
                                    Global.j8_number_4_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_4_1"));
                                    Global.j8_number_5_1 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_5_1"));
                                    Global.j8_number_1_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_1_2"));
                                    Global.j8_number_2_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_2_2"));
                                    Global.j8_number_4_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_4_2"));
                                    Global.j8_number_5_2 = cursor_j8f_numberchange.getString(cursor_j8f_numberchange.getColumnIndex("number_5_2"));
                                }
                                cursor_j8f_numberchange.close();
                                if(!Global.j8_point_1_2.equals("0")){
                                    point1_j8f = true;
                                    bt1.setVisibility(View.GONE);
                                    bt_c1.setBackgroundResource(R.drawable.button);
                                }
                                else {
                                    point1_j8f = false;
                                    bt1.setVisibility(View.VISIBLE);
                                    bt_c1.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j8_point_2_2.equals("0")){
                                    point2_j8f = true;
                                    bt2.setVisibility(View.GONE);
                                    bt_c2.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    point2_j8f = false;
                                    bt2.setVisibility(View.VISIBLE);
                                    bt_c2.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j8_point_3.equals("0")){
                                    point3_j8f = true;
                                    bt3.setVisibility(View.GONE);
                                    bt_c3.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    point3_j8f = false;
                                    bt3.setVisibility(View.VISIBLE);
                                    bt_c3.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j8_point_4_2.equals("0")){
                                    point4_j8f = true;
                                    bt4.setVisibility(View.GONE);
                                    bt_c4.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    point4_j8f = false;
                                    bt4.setVisibility(View.VISIBLE);
                                    bt_c4.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j8_point_5_2.equals("0")){
                                    point5_j8f = true;
                                    bt5.setVisibility(View.GONE);
                                    bt_c5.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    point5_j8f = false;
                                    bt5.setVisibility(View.VISIBLE);
                                    bt_c5.setBackgroundResource(R.drawable.button_color_change);
                                }
                            }
                        }).create();

                final AlertDialog dialog_num_j7q = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("J7-A机号")//设置对话框的标题
                        .setSingleChoiceItems(items_j7a, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item_click_j7a = items_j7a[which];
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
                                textView_2.setText(item_click_j7a);
                                Global.numberOfAirplane = item_click_j7a;
                                String[] args = {String.valueOf(item_click_j7a)};
                                //获取对应机号的外挂状态，用于切换图片或者按钮的显示
                                Cursor cursor_j7a_numberchange = db.rawQuery("select * from j7a where  j7a_number = ?",args);
                                if(cursor_j7a_numberchange.moveToFirst()) {
                                    Global.j7_point_1_1 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("point_1_1"));
                                    Global.j7_point_2 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("point_2"));
                                    Global.j7_point_3_1 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("point_3_1"));
                                    Global.j7_point_p = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("point_p"));
                                    Global.j7_point_1_2 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("point_1_2"));
                                    Global.j7_point_3_2 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("point_3_2"));
                                    Global.j7_number_1_1 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("number_1_1"));
                                    Global.j7_number_2 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("number_2"));
                                    Global.j7_number_3_1 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("number_3_1"));
                                    Global.j7_number_p = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("number_p"));
                                    Global.j7_number_1_2 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("number_1_2"));
                                    Global.j7_number_3_2 = cursor_j7a_numberchange.getString(cursor_j7a_numberchange.getColumnIndex("number_3_2"));
                                }
                                cursor_j7a_numberchange.close();
                                if(!Global.j7_point_1_2.equals("0")){
                                    point1_j7a = true;
                                    bt6.setVisibility(View.GONE);
                                    bt_c6.setBackgroundResource(R.drawable.button);
                                }
                                else {
                                    point1_j7a = false;
                                    bt6.setVisibility(View.VISIBLE);
                                    bt_c6.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j7_point_2.equals("0")){
                                    point2_j7a = true;
                                    bt7.setVisibility(View.GONE);
                                    bt_c7.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    point2_j7a = false;
                                    bt7.setVisibility(View.VISIBLE);
                                    bt_c7.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j7_point_3_2.equals("0")){
                                    point3_j7a = true;
                                    bt8.setVisibility(View.GONE);
                                    bt_c8.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    point3_j7a = false;
                                    bt8.setVisibility(View.VISIBLE);
                                    bt_c8.setBackgroundResource(R.drawable.button_color_change);
                                }
                                if(!Global.j7_point_p.equals("0")){
                                    pointp_j7a = true;
                                    bt_c9.setBackgroundResource(R.drawable.button);
                                }
                                else{
                                    pointp_j7a = false;
                                    bt_c9.setBackgroundResource(R.drawable.button_color_change);
                                }
                            }
                        }).create();

                if(Global.j8f == true && Global.j7a == false){
                    dialog_num_j8f.show();
                }
                else if(Global.j8f == false && Global.j7a == true){
                    dialog_num_j7q.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent1 = new Intent(MainActivity.this, ZbarActivity.class);
            startActivity(intent1);
        }  else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent4 = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_share) {
            Intent intent5 = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent5);
        } else if (id == R.id.nav_send) {
            Intent intent5 = new Intent(MainActivity.this, TroubleActivity.class);
            startActivity(intent5);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void transfrom(){
        Global.equipment_number = "";
        Global.equipment_type = "";
        Global.equipment_type_n = "";
        Global.equipment_used_on = "";
        Global.equipment_lifetime = "";
        Global.equipment_firstfixtime = "";
        Global.fixornot = "";
        Global.bigfix_time = "";
        Global.product_date = "";
        Global.used_count = "";
        Global.electric_conformity = "";
        Global.electric_checker = "";
        Global.electric_checkdate = "";
        Global.mechanical_conformity = "";
        Global.mechanical_checker = "";
        Global.mechanical_checkdate = "";
        Global.seal_conformity = "";
        Global.seal_checker = "";
        Global.seal_checkdate = "";
        Global.airplane_number = "";
        Global.equipment_state = "";
        Global.in_or_out = "";
        Global.change_date = "";
        Global.change_people = "";
        Global.fly_count = "";
        Global.shuoming = "";
        Global.wulicanshu = "";

        dbHelper = new DBHelper(this, "info_46h.db", null, 1);//创建数据库文件
        db = dbHelper.getReadableDatabase();//打开数据库文件并传递返回值给db
        if(Global.j8f){
            numberOf_j8[1] = Global.j8_number_1_2;
            numberOf_j8[2] = Global.j8_number_2_2;
            numberOf_j8[3] = Global.j8_number_3;
            numberOf_j8[4] = Global.j8_number_4_2;
            numberOf_j8[5] = Global.j8_number_5_2;
        String[] args = {String.valueOf(numberOf_j8[which_chosed])};
        Cursor numberGet = db.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
        if (numberGet.moveToFirst()) {
            Global.equipment_number = numberGet.getString(numberGet.getColumnIndex("equipment_number"));//编号
            Global.equipment_type = numberGet.getString(numberGet.getColumnIndex("equipment_type"));//类别
            Global.equipment_type_n = numberGet.getString(numberGet.getColumnIndex("equipment_type_n"));//型号
            Global.equipment_used_on = numberGet.getString(numberGet.getColumnIndex("equipment_used_on"));//适用机型
            Global.equipment_lifetime = numberGet.getString(numberGet.getColumnIndex("equipment_lifetime"));//规定寿命
            Global.equipment_firstfixtime = numberGet.getString(numberGet.getColumnIndex("equipment_firstfixtime"));//首翻期
            Global.fixornot = numberGet.getString(numberGet.getColumnIndex("fixornot"));//是否翻修
            Global.bigfix_time = numberGet.getString(numberGet.getColumnIndex("bigfix_time"));//翻修/大修时间
            Global.product_date = numberGet.getString(numberGet.getColumnIndex("product_date"));//出厂时间
            Global.used_count = numberGet.getString(numberGet.getColumnIndex("used_count"));//发射投放次数
            Global.electric_conformity = numberGet.getString(numberGet.getColumnIndex("electric_conformity"));//电气性能是否合格
            Global.electric_checker = numberGet.getString(numberGet.getColumnIndex("electric_checker"));//电气性能检查工作人
            Global.electric_checkdate = numberGet.getString(numberGet.getColumnIndex("electric_checkdate"));//电气性能检查时间
            Global.mechanical_conformity = numberGet.getString(numberGet.getColumnIndex("mechanical_conformity"));//机械性能是否合格
            Global.mechanical_checker = numberGet.getString(numberGet.getColumnIndex("mechanical_checker"));//机械性能检查工作人
            Global.mechanical_checkdate = numberGet.getString(numberGet.getColumnIndex("mechanical_checkdate"));//机械性能检查时间
            Global.seal_conformity = numberGet.getString(numberGet.getColumnIndex("seal_conformity"));//密封性能是否合格
            Global.seal_checker = numberGet.getString(numberGet.getColumnIndex("seal_checker"));//密封性能检查工作人
            Global.seal_checkdate = numberGet.getString(numberGet.getColumnIndex("seal_checkdate"));//密封性能检查时间
            Global.airplane_number = numberGet.getString(numberGet.getColumnIndex("airplane_number"));//主机号
            Global.equipment_state = numberGet.getString(numberGet.getColumnIndex("equipment_state"));//设备状态
            Global.in_or_out = numberGet.getString(numberGet.getColumnIndex("in_or_out"));//借用状态
            Global.change_date = numberGet.getString(numberGet.getColumnIndex("change_date"));//出入库时间
            Global.change_people = numberGet.getString(numberGet.getColumnIndex("change_people"));//最后一次操作人
            Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));//挂飞次数
            Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
            Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
            }
        }
        else if(Global.j7a){
            numberOf_j7[1] = Global.j7_number_1_2;
            numberOf_j7[2] = Global.j7_number_2;
            numberOf_j7[3] = Global.j7_number_3_2;
            numberOf_j7[4] = Global.j7_number_p;
            String[] args = {String.valueOf(numberOf_j7[which_chosed])};
            Cursor numberGet = db.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
            if (numberGet.moveToFirst()) {
                Global.equipment_number = numberGet.getString(numberGet.getColumnIndex("equipment_number"));//编号
                Global.equipment_type = numberGet.getString(numberGet.getColumnIndex("equipment_type"));//类别
                Global.equipment_type_n = numberGet.getString(numberGet.getColumnIndex("equipment_type_n"));//型号
                Global.equipment_used_on = numberGet.getString(numberGet.getColumnIndex("equipment_used_on"));//适用机型
                Global.equipment_lifetime = numberGet.getString(numberGet.getColumnIndex("equipment_lifetime"));//规定寿命
                Global.equipment_firstfixtime = numberGet.getString(numberGet.getColumnIndex("equipment_firstfixtime"));//首翻期
                Global.fixornot = numberGet.getString(numberGet.getColumnIndex("fixornot"));//是否翻修
                Global.bigfix_time = numberGet.getString(numberGet.getColumnIndex("bigfix_time"));//翻修/大修时间
                Global.product_date = numberGet.getString(numberGet.getColumnIndex("product_date"));//出厂时间
                Global.used_count = numberGet.getString(numberGet.getColumnIndex("used_count"));//发射投放次数
                Global.electric_conformity = numberGet.getString(numberGet.getColumnIndex("electric_conformity"));//电气性能是否合格
                Global.electric_checker = numberGet.getString(numberGet.getColumnIndex("electric_checker"));//电气性能检查工作人
                Global.electric_checkdate = numberGet.getString(numberGet.getColumnIndex("electric_checkdate"));//电气性能检查时间
                Global.mechanical_conformity = numberGet.getString(numberGet.getColumnIndex("mechanical_conformity"));//机械性能是否合格
                Global.mechanical_checker = numberGet.getString(numberGet.getColumnIndex("mechanical_checker"));//机械性能检查工作人
                Global.mechanical_checkdate = numberGet.getString(numberGet.getColumnIndex("mechanical_checkdate"));//机械性能检查时间
                Global.seal_conformity = numberGet.getString(numberGet.getColumnIndex("seal_conformity"));//密封性能是否合格
                Global.seal_checker = numberGet.getString(numberGet.getColumnIndex("seal_checker"));//密封性能检查工作人
                Global.seal_checkdate = numberGet.getString(numberGet.getColumnIndex("seal_checkdate"));//密封性能检查时间
                Global.airplane_number = numberGet.getString(numberGet.getColumnIndex("airplane_number"));//主机号
                Global.equipment_state = numberGet.getString(numberGet.getColumnIndex("equipment_state"));//设备状态
                Global.in_or_out = numberGet.getString(numberGet.getColumnIndex("in_or_out"));//借用状态
                Global.change_date = numberGet.getString(numberGet.getColumnIndex("change_date"));//出入库时间
                Global.change_people = numberGet.getString(numberGet.getColumnIndex("change_people"));//最后一次操作人
                Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));//挂飞次数
                Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
            }
        }
    }

    //人员信息初始化
    public void people_init(){
        dbHelper = new DBHelper(this, "info_46h.db", null, 1);//创建数据库文件
        db = dbHelper.getWritableDatabase();//打开数据库文件并传递返回值给db
        Cursor p = db.rawQuery("select * from peoples", null);//获取表peoples中的项目，通过返回值判断是否为空
        int amount_p = p.getCount();
        if (amount_p == 0){ //若为空进行初始化，由于数据量不大，不使用文件导入，直接插入进行初始化
            ContentValues value_p = new ContentValues();
            value_p.put("people_name","张海威");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","张超");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","翟亮");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","张艳清");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","苏嵋军");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","郭镇");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","宋金轩");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","于鑫");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","朱霖");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","陈崇琳");
            value_p.put("group_id","1");
            value_p.put("group_name","机务一中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","王樊");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","康家赫");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","廖树生");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","马权龙");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","刘进程");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","姜松年");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","周航");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","陈鹏");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","闫宇");
            value_p.put("group_id","2");
            value_p.put("group_name","机务二中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","于成浩");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","朱庆彪");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","王舵");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","韩朝伟");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","王鑫");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","代艳彬");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","刘建辉");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","袁绍春");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","郭瑞庚");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","石毅");
            value_p.put("group_id","3");
            value_p.put("group_name","机务三中队");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","安丰得");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","吕华君");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","崔志新");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","单志明");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","李建新");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","吴浩");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","李松林");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
            value_p.put("people_name","袁铭潮");
            value_p.put("group_id","4");
            value_p.put("group_name","修理厂");
            db.insert("peoples",null,value_p);
            value_p.clear();
        }
    }

    //j8f飞机信息初始化
    public void j8f_init() {
        dbHelper = new DBHelper(this, "info_46h.db", null, 1);//创建数据库文件
        db = dbHelper.getWritableDatabase();//打开数据库文件并传递返回值给db
        Cursor jc8fs = db.rawQuery("select * from jc8f", null);//获取表jc8f中的项目，通过返回值判断是否为空
        int amount_jc8fs = jc8fs.getCount();
        if (amount_jc8fs == 0) { //若为空进行初始化，由于数据量不大，不使用文件导入，直接插入进行初始化
            ContentValues value_jc8fs = new ContentValues();
            value_jc8fs.put("jc8f_number","01");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","02");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","03");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","04");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","05");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","06");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","07");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","08");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","09");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","10");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","11");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","12");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","13");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","14");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","15");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","16");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","17");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","19");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","20");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","21");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","22");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","23");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
            value_jc8fs.put("jc8f_number","24");
            value_jc8fs.put("jc8f_type","J8-F");
            value_jc8fs.put("point_1_1","0");
            value_jc8fs.put("number_1_1","000000");
            value_jc8fs.put("point_1_2","0");
            value_jc8fs.put("number_1_2","000000");
            value_jc8fs.put("point_2_1","0");
            value_jc8fs.put("number_2_1","000000");
            value_jc8fs.put("point_2_2","0");
            value_jc8fs.put("number_2_2","000000");
            value_jc8fs.put("point_3","0");
            value_jc8fs.put("number_3","000000");
            value_jc8fs.put("point_4_1","0");
            value_jc8fs.put("number_4_1","000000");
            value_jc8fs.put("point_4_2","0");
            value_jc8fs.put("number_4_2","000000");
            value_jc8fs.put("point_5_1","0");
            value_jc8fs.put("number_5_1","000000");
            value_jc8fs.put("point_5_2","0");
            value_jc8fs.put("number_5_2","000000");
            db.insert("jc8f",null,value_jc8fs);
            value_jc8fs.clear();
        }
    }

    //j8f飞机信息初始化
    public void j7a_init() {
        dbHelper = new DBHelper(this, "info_46h.db", null, 1);//创建数据库文件
        db = dbHelper.getWritableDatabase();//打开数据库文件并传递返回值给db
        Cursor j7as = db.rawQuery("select * from j7a", null);//获取表jc8f中的项目，通过返回值判断是否为空
        int amount_j7as = j7as.getCount();
        if (amount_j7as == 0) { //若为空进行初始化，由于数据量不大，不使用文件导入，直接插入进行初始化
            ContentValues value_j7as = new ContentValues();
            value_j7as.put("j7a_number","31");
            value_j7as.put("j7a_type","J7-A");
            value_j7as.put("point_1_1","0");
            value_j7as.put("number_1_1","000000");
            value_j7as.put("point_1_2","0");
            value_j7as.put("number_1_2","000000");
            value_j7as.put("point_2","0");
            value_j7as.put("number_2","000000");
            value_j7as.put("point_3_1","0");
            value_j7as.put("number_3_1","000000");
            value_j7as.put("point_3_2","0");
            value_j7as.put("number_3_2","000000");
            value_j7as.put("point_p","0");
            value_j7as.put("number_p","0000000");
            db.insert("j7a",null,value_j7as);
            value_j7as.clear();
            value_j7as.put("j7a_number","32");
            value_j7as.put("j7a_type","J7-A");
            value_j7as.put("point_1_1","0");
            value_j7as.put("number_1_1","000000");
            value_j7as.put("point_1_2","0");
            value_j7as.put("number_1_2","000000");
            value_j7as.put("point_2","0");
            value_j7as.put("number_2","000000");
            value_j7as.put("point_3_1","0");
            value_j7as.put("number_3_1","000000");
            value_j7as.put("point_3_2","0");
            value_j7as.put("number_3_2","000000");
            value_j7as.put("point_p","0");
            value_j7as.put("number_p","0000000");
            db.insert("j7a",null,value_j7as);
            value_j7as.clear();
            value_j7as.put("j7a_number","33");
            value_j7as.put("j7a_type","J7-A");
            value_j7as.put("point_1_1","0");
            value_j7as.put("number_1_1","000000");
            value_j7as.put("point_1_2","0");
            value_j7as.put("number_1_2","000000");
            value_j7as.put("point_2","0");
            value_j7as.put("number_2","000000");
            value_j7as.put("point_3_1","0");
            value_j7as.put("number_3_1","000000");
            value_j7as.put("point_3_2","0");
            value_j7as.put("number_3_2","000000");
            value_j7as.put("point_p","0");
            value_j7as.put("number_p","0000000");
            db.insert("j7a",null,value_j7as);
            value_j7as.clear();
            value_j7as.put("j7a_number","34");
            value_j7as.put("j7a_type","J7-A");
            value_j7as.put("point_1_1","0");
            value_j7as.put("number_1_1","000000");
            value_j7as.put("point_1_2","0");
            value_j7as.put("number_1_2","000000");
            value_j7as.put("point_2","0");
            value_j7as.put("number_2","000000");
            value_j7as.put("point_3_1","0");
            value_j7as.put("number_3_1","000000");
            value_j7as.put("point_3_2","0");
            value_j7as.put("number_3_2","000000");
            value_j7as.put("point_p","0");
            value_j7as.put("number_p","0000000");
            db.insert("j7a",null,value_j7as);
            value_j7as.clear();
        }
    }

}
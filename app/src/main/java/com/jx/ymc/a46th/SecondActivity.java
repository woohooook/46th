package com.jx.ymc.a46th;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CircleRefreshLayout mRefreshLayout;
    private com.github.clans.fab.FloatingActionButton fab_change_second;
    private boolean zhuangtai_second = true;

    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private ListView listview_display;
    private HashMap<String, String> map;
    String[] equipment_number = new String[1000];
    String[] equipment_type = new String[1000];
    String[] equipment_type_n = new String[1000];
    String[] airplane_number = new String[1000];
    String[] equipment_state = new String[1000];
    int[] img = new int[1000];
    private Long id_to_del;
    private BottomSheetDialog bottomSheet;
    private ImageButton guzhangshaixuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.second_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_second);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle("设备总览");
        setSupportActionBar(toolbar);

        inforead_byairnum();
        fab_change_second = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_change_second);
        fab_change_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zhuangtai_second){   //若zhuangtai_second变量为true，按照飞机展示外挂设备
                    zhuangtai_second = false;
                    fab_change_second.setImageResource(R.drawable.ic_view_stream_black_24dp);
                    inforead_byairnum();

                }
                else{   //若该变量为false，按照外挂类别展示
                    zhuangtai_second = true;
                    fab_change_second.setImageResource(R.drawable.ic_view_list_black_24dp);
                    inforead_bytype();
                }
            }
        });

        guzhangshaixuan = (ImageButton) findViewById(R.id.button_guzhang);
        guzhangshaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper_display = new DBHelper(SecondActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getWritableDatabase();
                listview_display = (ListView) SecondActivity.this.findViewById(R.id.list);
                String[] args_guzhang = {String.valueOf("故障")};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_state = ?", args_guzhang);
                int amount_display = 0;
                if (cursor.moveToFirst()) {
                    do {
                        String equipment_number_display = cursor.getString(cursor.getColumnIndex("equipment_number"));
                        equipment_number[amount_display] = equipment_number_display;
                        String equipment_type_display = cursor.getString(cursor.getColumnIndex("equipment_type"));
                        equipment_type[amount_display] = equipment_type_display;
                        String equipment_type_n_display = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                        equipment_type_n[amount_display] = equipment_type_n_display;
                        String airplane_number_display = cursor.getString(cursor.getColumnIndex("airplane_number"));
                        airplane_number[amount_display] = airplane_number_display;
                        String equipment_state_display = cursor.getString(cursor.getColumnIndex("equipment_state"));
                        equipment_state[amount_display] = equipment_state_display;
                        if(equipment_state_display.equals("故障")){
                            img[amount_display] = R.drawable.ic_assistant_photo_red_24dp;
                        }
                        else{
                            img[amount_display] = R.drawable.ic_star_black_24dp;
                        }
                        amount_display++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                //final ListView listView_t = (ListView) this.findViewById(R.id.listview_display);
                List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < amount_display; i++) {
                    Map<String, Object> listem = new HashMap<String, Object>();

                    listem.put("equipment_state", img[i]);
                    listem.put("airplane_number", airplane_number[i]);
                    listem.put("equipment_type_n", equipment_type_n[i]);
                    listem.put("equipment_type", equipment_type[i]);
                    listem.put("equipment_number", equipment_number[i]);
                    listems.add(listem);
                }
                SimpleAdapter simplead = new SimpleAdapter(SecondActivity.this, listems,
                        R.layout.display_item_byairnum, new String[]{"equipment_state","airplane_number", "equipment_type_n", "equipment_type", "equipment_number"},
                        new int[]{R.id.item_img,R.id.textView_display_byairnum_1, R.id.textView_display_byairnum_2, R.id.textView_display_byairnum_3, R.id.textView_display_byairnum_4});
                listview_display.setAdapter(simplead);
                listview_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        map = (HashMap<String, String>) parent.getItemAtPosition(position);
                        String[] args = {String.valueOf(map.get("equipment_number"))};
                        Cursor numberGet = display_database.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
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
                            Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));
                            Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                            Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
                        }
                        Intent intent_armment = new Intent(SecondActivity.this, DisplayActivity.class);
                        startActivity(intent_armment);
                    }
                });

                listview_display.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按监听以及底部按钮弹出
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        map = (HashMap<String, String>) parent.getItemAtPosition(position);
                        id_to_del = id;

                        bottomSheet = new BottomSheetDialog(SecondActivity.this);//实例化
                        bottomSheet.setContentView(R.layout.dialog_chose);//设置对框框中的布局
                        Button button_change = (Button) bottomSheet.findViewById(R.id.button_change);   //修改按钮监听
                        button_change.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String[] args = {String.valueOf(map.get("equipment_number"))};
                                Cursor numberGet = display_database.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
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
                                    Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));
                                    Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                                    Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
                                }
                                bottomSheet.dismiss();
                                Intent intent_armment = new Intent(SecondActivity.this, ArmamentActivity.class);
                                startActivity(intent_armment);
                            }
                        });
                        Button button_del = (Button) bottomSheet.findViewById(R.id.button_del);   //删除按钮监听
                        button_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String[] args = {String.valueOf(map.get("equipment_number"))};
                                display_database.execSQL("delete from armament where equipment_number = ?",args);
                                bottomSheet.dismiss();
                                Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
                                startActivity(intent);
                            }
                        });
                        Button button_cancel = (Button) bottomSheet.findViewById(R.id.button_cancel);   //取消按钮监听
                        button_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomSheet.dismiss();
                            }
                        });
                        bottomSheet.show();//显示弹窗，对应三个按钮的处理在下面
                        return true;
                    }
                });

            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                SecondActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_second);
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
        int id = item.getItemId();
        // Handle navigation view item clicks here.

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent1 = new Intent(SecondActivity.this, ZbarActivity.class);
            startActivity(intent1);
        }  else if (id == R.id.nav_slideshow) {
            Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent intent5 = new Intent(SecondActivity.this, InputActivity.class);
            startActivity(intent5);
        } else if (id == R.id.nav_send) {
            Intent intent5 = new Intent(SecondActivity.this, TroubleActivity.class);
            startActivity(intent5);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void inforead_byairnum() {
        dbHelper_display = new DBHelper(this, "info_46h.db", null, 1);
        display_database = dbHelper_display.getWritableDatabase();
        listview_display = (ListView) this.findViewById(R.id.list);
        Cursor cursor = display_database.rawQuery("select * from armament order by airplane_number", null);
        int amount_display = 0;
        if (cursor.moveToFirst()) {
            do {
                String equipment_number_display = cursor.getString(cursor.getColumnIndex("equipment_number"));
                equipment_number[amount_display] = equipment_number_display;
                String equipment_type_display = cursor.getString(cursor.getColumnIndex("equipment_type"));
                equipment_type[amount_display] = equipment_type_display;
                String equipment_type_n_display = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                equipment_type_n[amount_display] = equipment_type_n_display;
                String airplane_number_display = cursor.getString(cursor.getColumnIndex("airplane_number"));
                airplane_number[amount_display] = airplane_number_display;
                String equipment_state_display = cursor.getString(cursor.getColumnIndex("equipment_state"));
                equipment_state[amount_display] = equipment_state_display;
                if(equipment_state_display.equals("故障")){
                    img[amount_display] = R.drawable.ic_assistant_photo_red_24dp;
                }
                else{
                    img[amount_display] = R.drawable.ic_star_black_24dp;
                }
                amount_display++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        //final ListView listView_t = (ListView) this.findViewById(R.id.listview_display);
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < amount_display; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();

            listem.put("equipment_state", img[i]);
            listem.put("airplane_number", airplane_number[i]);
            listem.put("equipment_type_n", equipment_type_n[i]);
            listem.put("equipment_type", equipment_type[i]);
            listem.put("equipment_number", equipment_number[i]);
            listems.add(listem);
        }
        SimpleAdapter simplead = new SimpleAdapter(this, listems,
                R.layout.display_item_byairnum, new String[]{"equipment_state","airplane_number", "equipment_type_n", "equipment_type", "equipment_number"},
                new int[]{R.id.item_img,R.id.textView_display_byairnum_1, R.id.textView_display_byairnum_2, R.id.textView_display_byairnum_3, R.id.textView_display_byairnum_4});
        listview_display.setAdapter(simplead);
        listview_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map = (HashMap<String, String>) parent.getItemAtPosition(position);
                String[] args = {String.valueOf(map.get("equipment_number"))};
                Cursor numberGet = display_database.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
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
                    Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));
                    Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                    Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
                }
                Intent intent_armment = new Intent(SecondActivity.this, DisplayActivity.class);
                startActivity(intent_armment);
            }
        });

        listview_display.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按监听以及底部按钮弹出
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                map = (HashMap<String, String>) parent.getItemAtPosition(position);
                id_to_del = id;

                bottomSheet = new BottomSheetDialog(SecondActivity.this);//实例化
                bottomSheet.setContentView(R.layout.dialog_chose);//设置对框框中的布局
                Button button_change = (Button) bottomSheet.findViewById(R.id.button_change);   //修改按钮监听
                button_change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] args = {String.valueOf(map.get("equipment_number"))};
                        Cursor numberGet = display_database.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
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
                            Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));
                            Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                            Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
                        }
                        bottomSheet.dismiss();
                        Intent intent_armment = new Intent(SecondActivity.this, ArmamentActivity.class);
                        startActivity(intent_armment);
                    }
                });
                Button button_del = (Button) bottomSheet.findViewById(R.id.button_del);   //删除按钮监听
                button_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] args = {String.valueOf(map.get("equipment_number"))};
                        display_database.execSQL("delete from armament where equipment_number = ?",args);
                        bottomSheet.dismiss();
                        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });
                Button button_cancel = (Button) bottomSheet.findViewById(R.id.button_cancel);   //取消按钮监听
                button_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheet.dismiss();
                    }
                });
                bottomSheet.show();//显示弹窗，对应三个按钮的处理在下面
                return true;
            }
        });

    }

    public void inforead_bytype() {
        dbHelper_display = new DBHelper(this, "info_46h.db", null, 1);
        display_database = dbHelper_display.getWritableDatabase();
        listview_display = (ListView) this.findViewById(R.id.list);
        Cursor cursor = display_database.rawQuery("select * from armament order by equipment_type", null);
        int amount_display = 0;
        if (cursor.moveToFirst()) {
            do {
                String equipment_number_display = cursor.getString(cursor.getColumnIndex("equipment_number"));
                equipment_number[amount_display] = equipment_number_display;
                String equipment_type_display = cursor.getString(cursor.getColumnIndex("equipment_type"));
                equipment_type[amount_display] = equipment_type_display;
                String equipment_type_n_display = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                equipment_type_n[amount_display] = equipment_type_n_display;
                String airplane_number_display = cursor.getString(cursor.getColumnIndex("airplane_number"));
                airplane_number[amount_display] = airplane_number_display;
                String equipment_state_display = cursor.getString(cursor.getColumnIndex("equipment_state"));
                equipment_state[amount_display] = equipment_state_display;
                if(equipment_state_display.equals("故障")){
                    img[amount_display] = R.drawable.ic_assistant_photo_red_24dp;
                }
                else{
                    img[amount_display] = R.drawable.ic_star_black_24dp;
                }
                amount_display++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        //final ListView listView_t = (ListView) this.findViewById(R.id.listview_display);
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < amount_display; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();

            listem.put("equipment_state", img[i]);
            listem.put("airplane_number", airplane_number[i]);
            listem.put("equipment_type_n", equipment_type_n[i]);
            listem.put("equipment_type", equipment_type[i]);
            listem.put("equipment_number", equipment_number[i]);
            listems.add(listem);
        }
        SimpleAdapter simplead = new SimpleAdapter(this, listems,
                R.layout.display_item_byairnum, new String[]{"equipment_state","airplane_number", "equipment_type_n", "equipment_type", "equipment_number"},
                new int[]{R.id.item_img,R.id.textView_display_byairnum_1, R.id.textView_display_byairnum_2, R.id.textView_display_byairnum_3, R.id.textView_display_byairnum_4});
        listview_display.setAdapter(simplead);
        listview_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map = (HashMap<String, String>) parent.getItemAtPosition(position);
                String[] args = {String.valueOf(map.get("equipment_number"))};
                Cursor numberGet = display_database.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
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
                    Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));
                    Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                    Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
                }
                Intent intent_armment = new Intent(SecondActivity.this, DisplayActivity.class);
                startActivity(intent_armment);
            }
        });

        listview_display.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按监听以及底部按钮弹出
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                map = (HashMap<String, String>) parent.getItemAtPosition(position);
                id_to_del = id;

                bottomSheet = new BottomSheetDialog(SecondActivity.this);//实例化
                bottomSheet.setContentView(R.layout.dialog_chose);//设置对框框中的布局
                Button button_change = (Button) bottomSheet.findViewById(R.id.button_change);   //修改按钮监听
                button_change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] args = {String.valueOf(map.get("equipment_number"))};
                        Cursor numberGet = display_database.rawQuery("select * from armament where equipment_number = ?", args);//获取表peoples中的项目，通过返回值判断是否为空
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
                            Global.fly_count = numberGet.getString(numberGet.getColumnIndex("fly_count"));
                            Global.shuoming = numberGet.getString(numberGet.getColumnIndex("shuoming"));//故障描述
                            Global.wulicanshu = numberGet.getString(numberGet.getColumnIndex("wulicanshu"));//物理参数
                        }
                        bottomSheet.dismiss();
                        Intent intent_armment = new Intent(SecondActivity.this, ArmamentActivity.class);
                        startActivity(intent_armment);
                    }
                });
                Button button_del = (Button) bottomSheet.findViewById(R.id.button_del);   //删除按钮监听
                button_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] args = {String.valueOf(map.get("equipment_number"))};
                        display_database.execSQL("delete from armament where equipment_number = ?",args);
                        bottomSheet.dismiss();
                        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });
                Button button_cancel = (Button) bottomSheet.findViewById(R.id.button_cancel);   //取消按钮监听
                button_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheet.dismiss();
                    }
                });
                bottomSheet.show();//显示弹窗，对应三个按钮的处理在下面
                return true;
            }
        });

    }

}
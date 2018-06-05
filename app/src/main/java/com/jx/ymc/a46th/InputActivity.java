package com.jx.ymc.a46th;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class InputActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private com.github.clans.fab.FloatingActionButton fab_change_second;

    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private ListView listview_display;
    private HashMap<String, String> map;
    private String[] equipment_number = new String[1000];
    private String[] equipment_type = new String[1000];
    private String[] equipment_type_n = new String[1000];
    private String[] airplane_number = new String[1000];
    private String[] equipment_used_on = new String[1000];
    private String[] equipment_lifetime = new String[1000];
    private String[] equipment_firstfixtime = new String[1000];
    private String[] fixornot = new String[1000];
    private String[] bigfix_time = new String[1000];
    private String[] product_date = new String[1000];
    private String[] used_count = new String[1000];
    private int[] used_count_i = new int[1000];
    private String[] electric_conformity = new String[1000];
    private String[] electric_checker = new String[1000];
    private String[] electric_checkdate = new String[1000];
    private String[] mechanical_conformity = new String[1000];
    private String[] mechanical_checker = new String[1000];
    private String[] mechanical_checkdate = new String[1000];
    private String[] seal_conformity = new String[1000];
    private String[] seal_checker = new String[1000];
    private String[] seal_checkdate = new String[1000];
    private String[] equipment_state = new String[1000];
    private String[] in_or_out = new String[1000];
    private String[] change_date = new String[1000];
    private String[] change_people = new String[1000];
    private String[] fly_count = new String[1000];
    private int[] fly_count_i = new int[1000];
    private String[] shuoming = new String[1000];
    private String[] wulicanshu = new String[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.input_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_input);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle("数据导入");
        setSupportActionBar(toolbar);

        try {
            readExcelToDB(InputActivity.this);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            Toast.makeText(InputActivity.this, "炸了", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            Toast.makeText(InputActivity.this, "没找着", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(InputActivity.this, "炸了...炸了", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                InputActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_input);
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
            Intent intent1 = new Intent(InputActivity.this, ZbarActivity.class);
            startActivity(intent1);
        } else if (id == R.id.nav_slideshow) {
            Intent intent2 = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_manage) {
            Intent intent3 = new Intent(InputActivity.this, SecondActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent5 = new Intent(InputActivity.this, TroubleActivity.class);
            startActivity(intent5);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void readExcelToDB(Context context)throws Exception {
        dbHelper_display = new DBHelper(this, "info_46h.db", null, 1);
        display_database = dbHelper_display.getWritableDatabase();
        //SharedPreferences sharedPreferences=context.getSharedPreferences("excel",Context.MODE_PRIVATE);
        //Boolean noConfig=sharedPreferences.getBoolean("readExcel",true);//默认是没有保存过
        //if(noConfig) {//如果没有保存过而且数据库parkInfo表的内容为空

                //我们把excel放在Assset目录下，通过Workbook.getWorkbook(inputStream);获取到整个excel。
        //Environment.getExternalStorageDirectory().getAbsolutePath();
        //String fileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"jx_46h.xls";
        //FileInputStream fos=openFileOutput(fileName);

        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(InputActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    InputActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        File sdcard = Environment.getExternalStorageDirectory();
        File myfile = new File(sdcard,"jx46h.xls");
        String SDCarePath=Environment.getExternalStorageDirectory().toString();
        String path=SDCarePath+"/jx46h.xls";
        //InputStream inputStream = context.getAssets().open("jx_46h.xls");
        String fileName = "/storage/emulated/0/jx_46h/jx46h.xls";
        String path_1 = Environment.getExternalStorageDirectory().getPath();
        String path_2 = path_1 + "/jx46h.xls";
        File is = new File(Environment.getExternalStorageDirectory().getPath() + "jx46h.xls");
            Workbook workbook = Workbook.getWorkbook(myfile);
                //获取第一张excel数据表。
                Sheet sheet = workbook.getSheet(0);
                int rows = sheet.getRows();//获取该表中有多少行数据。
                int num_read = 0;
                for (int i = 1; i < rows; i++) {
                    //sheet.getCell(0,i))，在这里i表示第几行数据，012346表示第几列，从0开始算。
                    equipment_type[i] = (sheet.getCell(0, i)).getContents();
                    equipment_type_n[i] = (sheet.getCell(1, i)).getContents();
                    equipment_used_on[i] = (sheet.getCell(2, i)).getContents();
                    equipment_lifetime[i] = (sheet.getCell(3, i)).getContents();
                    equipment_firstfixtime[i] = (sheet.getCell(4, i)).getContents();
                    fixornot[i] = (sheet.getCell(5, i)).getContents();
                    bigfix_time[i] = (sheet.getCell(6, i)).getContents();
                    equipment_number[i] = (sheet.getCell(7, i)).getContents();
                    product_date[i] = (sheet.getCell(8, i)).getContents();
                    used_count[i] = (sheet.getCell(9, i)).getContents();
                    //used_count_i[i] = Integer.valueOf(used_count[i]).intValue();
                    electric_conformity[i] = (sheet.getCell(10, i)).getContents();
                    electric_checker[i] = (sheet.getCell(11, i)).getContents();
                    electric_checkdate[i] = (sheet.getCell(12, i)).getContents();
                    mechanical_conformity [i] = (sheet.getCell(13, i)).getContents();
                    mechanical_checker[i] = (sheet.getCell(14, i)).getContents();
                    mechanical_checkdate[i] = (sheet.getCell(15, i)).getContents();
                    seal_conformity [i] = (sheet.getCell(16, i)).getContents();
                    seal_checker[i] = (sheet.getCell(17, i)).getContents();
                    seal_checkdate[i] = (sheet.getCell(18, i)).getContents();
                    airplane_number[i] = (sheet.getCell(19, i)).getContents();
                    equipment_state[i] = (sheet.getCell(20, i)).getContents();
                    in_or_out[i] = (sheet.getCell(21, i)).getContents();
                    change_date[i] = (sheet.getCell(22, i)).getContents();
                    change_people[i] = (sheet.getCell(23, i)).getContents();
                    fly_count[i] = (sheet.getCell(24, i)).getContents();
                    shuoming[i] = (sheet.getCell(25, i)).getContents();
                    wulicanshu[i] = (sheet.getCell(26, i)).getContents();
                    if(!equipment_number[i].equals("")) {
                        ContentValues value_armament = new ContentValues();
                        value_armament.put("equipment_number", equipment_number[i]);
                        value_armament.put("equipment_type", equipment_type[i]);
                        value_armament.put("equipment_type_n", equipment_type_n[i]);
                        value_armament.put("equipment_used_on", equipment_used_on[i]);
                        value_armament.put("equipment_lifetime", equipment_lifetime[i]);
                        value_armament.put("equipment_firstfixtime", equipment_firstfixtime[i]);
                        value_armament.put("fixornot", fixornot[i]);
                        value_armament.put("bigfix_time", bigfix_time[i]);
                        value_armament.put("product_date", product_date[i]);
                        value_armament.put("used_count", used_count[i]);
                        value_armament.put("electric_conformity", electric_conformity[i]);
                        value_armament.put("electric_checker", electric_checker[i]);
                        value_armament.put("electric_checkdate", electric_checkdate[i]);
                        value_armament.put("mechanical_conformity", mechanical_conformity[i]);
                        value_armament.put("mechanical_checker", mechanical_checker[i]);
                        value_armament.put("mechanical_checkdate", mechanical_checkdate[i]);
                        value_armament.put("seal_conformity", seal_conformity[i]);
                        value_armament.put("seal_checker", seal_checker[i]);
                        value_armament.put("seal_checkdate", seal_checkdate[i]);
                        value_armament.put("airplane_number", airplane_number[i]);
                        value_armament.put("equipment_state", equipment_state[i]);
                        value_armament.put("in_or_out", in_or_out[i]);
                        value_armament.put("change_date", change_date[i]);
                        value_armament.put("change_people", change_people[i]);
                        value_armament.put("fly_count", fly_count[i]);
                        value_armament.put("shuoming", shuoming[i]);
                        value_armament.put("wulicanshu", wulicanshu[i]);
                        //根据编号判断是否存在，再插入,且不能为空
                        String[] args = {String.valueOf(equipment_number[i])};
                        Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args );
                        boolean resule = null != cursor && cursor.moveToFirst() ;
                        if(!resule) {
                            display_database.insert("armament", null, value_armament);
                            num_read++;
                        }
                        value_armament.clear();/**/
                        //Log.d("读取中第--",equipment_number[i]);
                        }
                    }
                //sharedPreferences.edit().putBoolean("readExcel", false).commit();//读取完毕后，把记录置为不读。
        if(num_read != 0) {
            Toast.makeText(context, "成功读取", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context, "没有新数据", Toast.LENGTH_LONG).show();
        }
        //}
    }
}
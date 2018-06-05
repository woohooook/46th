package com.jx.ymc.a46th;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.lang.String;
import jxl.write.WriteException;


public class TroubleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private com.github.clans.fab.FloatingActionButton fab_change_second;

    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private ListView listview_display;
    private HashMap<String, String> map;
    private TextView log_out;

    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.trouble_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_trouble);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle("导出");
        setSupportActionBar(toolbar);

        Button inOrOut = (Button) findViewById(R.id.inOrOut);
        inOrOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writeExcel_trouble(TroubleActivity.this, "出入库信息");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(TroubleActivity.this, "炸了...炸了", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        Button armament_out = (Button) findViewById(R.id.armament_out);
        armament_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writeExcel_armament(TroubleActivity.this, "设备信息");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(TroubleActivity.this, "炸了...炸了", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                TroubleActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_trouble);
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
            Intent intent1 = new Intent(TroubleActivity.this, ZbarActivity.class);
            startActivity(intent1);
        } else if (id == R.id.nav_slideshow) {
            Intent intent2 = new Intent(TroubleActivity.this, MainActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_manage) {
            Intent intent3 = new Intent(TroubleActivity.this, SecondActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_share) {
            Intent intent4 = new Intent(TroubleActivity.this, InputActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static void writeExcel_trouble(Context context, String fileName) throws Exception {

        String[] caozuoren = new String[10000];
        String[] date = new String[10000];
        String[] caozuo = new String[10000];
        String[] xinghao = new String[10000];
        String[] leixing = new String[10000];
        String[] bianhao = new String[10000];
        String[] yongtu = new String[10000];
        int amount_p = 0;
        DBHelper dbHelper_trouble = new DBHelper(context, "info_46h.db", null, 1);
        SQLiteDatabase trouble_database = dbHelper_trouble.getWritableDatabase();
        Cursor cursor = trouble_database.rawQuery("select * from troubles order by trouble_id",null );
        if (cursor.moveToFirst()){
            do{
                String caozuoren_display = cursor.getString(cursor.getColumnIndex("caozuoren"));
                caozuoren[amount_p] = caozuoren_display;
                String date_display = cursor.getString(cursor.getColumnIndex("date"));
                date[amount_p] = date_display;
                String caozuo_display = cursor.getString(cursor.getColumnIndex("caozuo"));
                caozuo[amount_p] = caozuo_display;
                String xinghao_display = cursor.getString(cursor.getColumnIndex("xinghao"));
                xinghao[amount_p] = xinghao_display;
                String leixing_display = cursor.getString(cursor.getColumnIndex("leixing"));
                leixing[amount_p] = leixing_display;
                String bianhao_display = cursor.getString(cursor.getColumnIndex("bianhao"));
                bianhao[amount_p] = bianhao_display;
                String yongtu_display = cursor.getString(cursor.getColumnIndex("yongtu"));
                yongtu[amount_p] = yongtu_display;
                amount_p ++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        String[] title1 = { "日期", "操作人", "操作","型号","类型","编号","说明" };
        File file;
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (file.exists()){
            file.delete();
        }
        // 创建Excel工作表
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet1 = wwb.createSheet("出入库信息", 0);
        Label label1;
        for (int i = 0; i < title1.length; i++) {
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label1 = new Label(i, 0, title1[i], getHeader());
            // 将定义好的单元格添加到工作表中
            sheet1.addCell(label1);
        }
        for (int i = 0; i < amount_p; i++) {

            Label date_l= new Label(0, i + 1, date[i]);
            Label caozuoren_l = new Label(1,i+1,caozuoren[i]);
            Label caozuo_l = new Label(2, i + 1, caozuo[i]);
            Label xinghao_l= new Label(3, i + 1, xinghao[i]);
            Label leixing_l = new Label(4,i+1, leixing[i]);
            Label bianhao_l = new Label(5, i + 1, bianhao[i]);
            Label yongtu_l = new Label(6, i + 1, yongtu[i]);
            sheet1.addCell(date_l);
            sheet1.addCell(caozuoren_l);
            sheet1.addCell(caozuo_l);
            sheet1.addCell(xinghao_l);
            sheet1.addCell(leixing_l);
            sheet1.addCell(bianhao_l);
            sheet1.addCell(yongtu_l);
        }

        wwb.write();
        String log = "成功写入" + amount_p + "条出入库信息";
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
        // 关闭文件
        wwb.close();
    }

    public static void writeExcel_armament(Context context, String fileName) throws Exception {

        String[] equipment_number = new String[1000];//编号
        String[] equipment_type = new String[1000];//类别
        String[] equipment_type_n = new String[1000];//型号
        String[] equipment_used_on = new String[1000];//适用机型
        String[] equipment_lifetime = new String[1000];//规定寿命
        String[] equipment_firstfixtime = new String[1000];//首翻期
        String[] fixornot = new String[1000];//是否翻修
        String[] bigfix_time = new String[1000];//翻修/大修时间
        String[] product_date = new String[1000];//出厂时间
        String[]  used_count = new String[1000];//发射投放次数
        String[] electric_conformity = new String[1000];//电气性能是否合格
        String[] electric_checker = new String[1000];//电气性能检查工作人
        String[] electric_checkdate = new String[1000];//电气性能检查时间
        String[] mechanical_conformity = new String[1000];//机械性能是否合格
        String[] mechanical_checker = new String[1000];//机械性能检查工作人
        String[] mechanical_checkdate = new String[1000];//机械性能检查时间
        String[] seal_conformity = new String[1000];//密封性能是否合格
        String[] seal_checker = new String[1000];//密封性能检查工作人
        String[] seal_checkdate = new String[1000];//密封性能检查时间
        String[] airplane_number = new String[1000];//主机号
        String[] equipment_state = new String[1000];//设备状态
        String[] in_or_out = new String[1000];//借用状态
        String[] change_date = new String[1000];//出入库时间
        String[] change_people = new String[1000];//最后一次操作人
        String[] fly_count = new String[1000];//挂飞次数
        String[] shuoming = new String[1000];//故障描述
        String[] wulicanshu = new String[1000];//故障描述
        int amount_a = 0;
        DBHelper dbHelper_armament = new DBHelper(context, "info_46h.db", null, 1);
        SQLiteDatabase armament_database = dbHelper_armament.getWritableDatabase();
        Cursor cursor = armament_database.rawQuery("select * from armament order by armament_id",null );
        if (cursor.moveToFirst()){
            do{
                String equipment_number_armament = cursor.getString(cursor.getColumnIndex("equipment_number"));
                equipment_number[amount_a] = equipment_number_armament;
                String equipment_type_armament = cursor.getString(cursor.getColumnIndex("equipment_type"));
                equipment_type[amount_a] = equipment_type_armament;
                String equipment_type_n_armament = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                equipment_type_n[amount_a] = equipment_type_n_armament;
                String equipment_used_on_armament = cursor.getString(cursor.getColumnIndex("equipment_used_on"));
                equipment_used_on[amount_a] = equipment_used_on_armament;
                String equipment_lifetime_armament = cursor.getString(cursor.getColumnIndex("equipment_lifetime"));
                equipment_lifetime[amount_a] = equipment_lifetime_armament;
                String equipment_firstfixtime_armament = cursor.getString(cursor.getColumnIndex("equipment_firstfixtime"));
                equipment_firstfixtime[amount_a] = equipment_firstfixtime_armament;
                String fixornot_armament = cursor.getString(cursor.getColumnIndex("fixornot"));
                fixornot[amount_a] = fixornot_armament;
                String bigfix_time_armament = cursor.getString(cursor.getColumnIndex("bigfix_time"));
                bigfix_time[amount_a] = bigfix_time_armament;
                String product_date_armament = cursor.getString(cursor.getColumnIndex("product_date"));
                product_date[amount_a] = product_date_armament;
                String used_count_armament = cursor.getString(cursor.getColumnIndex("used_count"));
                used_count[amount_a] = used_count_armament;
                String electric_conformity_armament = cursor.getString(cursor.getColumnIndex("electric_conformity"));
                electric_conformity[amount_a] = electric_conformity_armament;
                String electric_checker_armament = cursor.getString(cursor.getColumnIndex("electric_checker"));
                electric_checker[amount_a] = electric_checker_armament;
                String electric_checkdate_armament = cursor.getString(cursor.getColumnIndex("electric_checkdate"));
                electric_checkdate[amount_a] = electric_checkdate_armament;
                String mechanical_conformity_armament = cursor.getString(cursor.getColumnIndex("mechanical_conformity"));
                mechanical_conformity[amount_a] = mechanical_conformity_armament;
                String mechanical_checker_armament = cursor.getString(cursor.getColumnIndex("mechanical_checker"));
                mechanical_checker[amount_a] = mechanical_checker_armament;
                String mechanical_checkdate_armament = cursor.getString(cursor.getColumnIndex("mechanical_checkdate"));
                mechanical_checkdate[amount_a] = mechanical_checkdate_armament;
                String seal_conformity_armament = cursor.getString(cursor.getColumnIndex("seal_conformity"));
                seal_conformity[amount_a] = seal_conformity_armament;
                String seal_checker_armament = cursor.getString(cursor.getColumnIndex("seal_checker"));
                seal_checker[amount_a] = seal_checker_armament;
                String seal_checkdate_armament = cursor.getString(cursor.getColumnIndex("seal_checkdate"));
                seal_checkdate[amount_a] = seal_checkdate_armament;
                String airplane_number_armament = cursor.getString(cursor.getColumnIndex("airplane_number"));
                airplane_number[amount_a] = airplane_number_armament;
                String equipment_state_armament = cursor.getString(cursor.getColumnIndex("equipment_state"));
                equipment_state[amount_a] = equipment_state_armament;
                String in_or_out_armament = cursor.getString(cursor.getColumnIndex("in_or_out"));
                in_or_out[amount_a] = in_or_out_armament;
                String change_date_armament = cursor.getString(cursor.getColumnIndex("change_date"));
                change_date[amount_a] = change_date_armament;
                String change_people_armament = cursor.getString(cursor.getColumnIndex("change_people"));
                change_people[amount_a] = change_people_armament;
                String fly_count_armament = cursor.getString(cursor.getColumnIndex("fly_count"));
                fly_count[amount_a] = fly_count_armament;
                String shuoming_armament = cursor.getString(cursor.getColumnIndex("shuoming"));
                shuoming[amount_a] = shuoming_armament;
                String wulicanshu_armament = cursor.getString(cursor.getColumnIndex("wulicanshu"));
                wulicanshu[amount_a] = wulicanshu_armament;
                amount_a ++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        String[] title1 = {"编号", "设备类型", "设备型号", "适用机型",  "规定寿命", "首翻期",
                "是否翻修", "翻修/大修时间", "出厂时间", "发射投放次数", "电气性能",
                "工作人", "检查时间", "机械性能", "工作人", "检查时间", "密封性能",
                "工作人", "检查时间", "主机号","设备状态", "借用状态", "出入库时间",
                "操作人","挂飞次数","故障描述","物理参数"};
        File file;
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (file.exists()){
            file.delete();
        }
        // 创建Excel工作表
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet1 = wwb.createSheet("设备信息", 0);
        Label label1;
        for (int i = 0; i < title1.length; i++) {
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label1 = new Label(i, 0, title1[i], getHeader());
            // 将定义好的单元格添加到工作表中
            sheet1.addCell(label1);
        }
        for (int i = 0; i < amount_a; i++) {

            Label equipment_number_l= new Label(0, i + 1, equipment_number[i]);
            Label equipment_type_l = new Label(1,i+1,equipment_type[i]);
            Label equipment_type_n_l = new Label(2, i + 1, equipment_type_n[i]);
            Label equipment_used_on_l= new Label(3, i + 1, equipment_used_on[i]);
            Label equipment_lifetime_l = new Label(4,i+1, equipment_lifetime[i]);
            Label equipment_firstfixtime_l = new Label(5, i + 1, equipment_firstfixtime[i]);
            Label fixornot_l = new Label(6, i + 1, fixornot[i]);
            Label bigfix_time_l= new Label(7, i + 1, bigfix_time[i]);
            Label product_date_l = new Label(8,i+1,product_date[i]);
            Label used_count_l = new Label(9, i + 1, used_count[i]);
            Label electric_conformity_l= new Label(10, i + 1, electric_conformity[i]);
            Label electric_checker_l = new Label(11,i+1, electric_checker[i]);
            Label electric_checkdate_l = new Label(12, i + 1, electric_checkdate[i]);
            Label mechanical_conformity_l = new Label(13, i + 1, mechanical_conformity[i]);
            Label mechanical_checker_l= new Label(14, i + 1, mechanical_checker[i]);
            Label mechanical_checkdate_l = new Label(15,i+1,mechanical_checkdate[i]);
            Label seal_conformity_l = new Label(16, i + 1, seal_conformity[i]);
            Label seal_checker_l= new Label(17, i + 1, seal_checker[i]);
            Label seal_checkdate_l = new Label(18,i+1, seal_checkdate[i]);
            Label airplane_number_l = new Label(19, i + 1, airplane_number[i]);
            Label equipment_state_l= new Label(20, i + 1, equipment_state[i]);
            Label in_or_out_l= new Label(21, i + 1, in_or_out[i]);
            Label change_date_l = new Label(22,i+1,change_date[i]);
            Label change_people_l = new Label(23, i + 1, change_people[i]);
            Label fly_count_l= new Label(24, i + 1, fly_count[i]);
            Label shuoming_l = new Label(25,i+1, shuoming[i]);
            Label wulicanshu_l = new Label(26,i+1, wulicanshu[i]);
            sheet1.addCell(equipment_number_l);
            sheet1.addCell(equipment_type_l);
            sheet1.addCell(equipment_type_n_l);
            sheet1.addCell(equipment_used_on_l);
            sheet1.addCell(equipment_lifetime_l);
            sheet1.addCell(equipment_firstfixtime_l);
            sheet1.addCell(fixornot_l);
            sheet1.addCell(bigfix_time_l);
            sheet1.addCell(product_date_l);
            sheet1.addCell(used_count_l);
            sheet1.addCell(electric_conformity_l);
            sheet1.addCell(electric_checker_l);
            sheet1.addCell(electric_checkdate_l);
            sheet1.addCell(mechanical_conformity_l);
            sheet1.addCell(mechanical_checker_l);
            sheet1.addCell(mechanical_checkdate_l);
            sheet1.addCell(seal_conformity_l);
            sheet1.addCell(seal_checker_l);
            sheet1.addCell(seal_checkdate_l);
            sheet1.addCell(airplane_number_l);
            sheet1.addCell(equipment_state_l);
            sheet1.addCell(in_or_out_l);
            sheet1.addCell(change_date_l);
            sheet1.addCell(change_people_l);
            sheet1.addCell(fly_count_l);
            sheet1.addCell(shuoming_l);
            sheet1.addCell(wulicanshu_l);
        }

        wwb.write();
        String log = "成功写入" + amount_a + "条设备信息";
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
        // 关闭文件
        wwb.close();
    }

    public static WritableCellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLUE);// 蓝色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    /** 获取SD可用容量 */
    private static long getAvailableStorage() {

        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        return availableSize;
    }


}
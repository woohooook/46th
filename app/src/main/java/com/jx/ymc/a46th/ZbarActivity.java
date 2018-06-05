package com.jx.ymc.a46th;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;

import android.graphics.Bitmap;

import com.github.clans.fab.FloatingActionButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ZbarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private com.github.clans.fab.FloatingActionButton fab_zxing;

    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private ListView listview_display;
    private HashMap<String, String> map;
    private int REQUEST_CODE_SCAN = 111;
    private String numberFromScan, equipment_type_n_zxing, equipment_type_zxing,
            airplane_number_zxing, equipment_state_zxing, in_or_out_zxing,
            people_zxing, trouble_zxing, date_zxing, caozuo_zxing;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    TextView qrCoded;
    ImageView qrCodeImage;
    ImageButton scanner, searcher, date;
    Button write_zxing;
    EditText qrCodeUrl;
    EditText tv_test, tv_people, tv_trouble;
    TextView tv_zxing_1, tv_zxing_2, tv_zxing_3, tv_zxing_4, tv_zxing_5, tv_zxing_6, tv_czr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.zbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_zBar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle("出入库");
        setSupportActionBar(toolbar);

        fab_zxing = (FloatingActionButton) findViewById(R.id.fab_zxing);
        scanner = (ImageButton) findViewById(R.id.ECoder_scaning);
        searcher= (ImageButton) findViewById(R.id.ECoder_finding);
        write_zxing = (Button) findViewById(R.id.write_zxing);
        date = (ImageButton) findViewById(R.id.date_zxing);
        tv_test = (EditText) findViewById(R.id.tv_test);
        tv_people = (EditText) findViewById(R.id.tv_people);
        tv_trouble = (EditText) findViewById(R.id.tv_trouble);
        tv_zxing_1 = (TextView) findViewById(R.id.tv_zing_1);
        tv_zxing_2 = (TextView) findViewById(R.id.tv_zing_2);
        tv_zxing_3 = (TextView) findViewById(R.id.tv_zing_3);
        tv_zxing_4 = (TextView) findViewById(R.id.tv_zing_4);
        tv_zxing_5 = (TextView) findViewById(R.id.tv_zing_5);
        tv_zxing_6 = (TextView) findViewById(R.id.tv_zing_6);
        tv_czr = (TextView) findViewById(R.id.tv_czr);

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(ZbarActivity.this)
                        .setOrientationLocked(false)
                        .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是ScanActivity
                        .initiateScan(); // 初始化扫描
            }
        });

        searcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_test.getText().toString().equals("")){
                    Toast.makeText(ZbarActivity.this, "编号不能为空！", Toast.LENGTH_LONG).show();
                }else {
                    numberFromScan = tv_test.getText().toString();
                    dbHelper_display = new DBHelper(ZbarActivity.this, "info_46h.db", null, 1);
                    display_database = dbHelper_display.getWritableDatabase();
                    String[] args = {String.valueOf(numberFromScan)};
                    Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args);
                    if(cursor.getCount()!= 0) {
                        if (cursor.moveToFirst()) {
                            equipment_type_n_zxing = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                            equipment_type_zxing = cursor.getString(cursor.getColumnIndex("equipment_type"));
                            airplane_number_zxing = cursor.getString(cursor.getColumnIndex("airplane_number"));
                            equipment_state_zxing = cursor.getString(cursor.getColumnIndex("equipment_state"));
                            in_or_out_zxing = cursor.getString(cursor.getColumnIndex("in_or_out"));
                        }
                        tv_zxing_1.setText(equipment_type_n_zxing);
                        tv_zxing_2.setText(equipment_type_zxing);
                        tv_zxing_3.setText(airplane_number_zxing);
                        tv_zxing_4.setText(equipment_state_zxing);
                        tv_zxing_5.setText(in_or_out_zxing);
                        if (in_or_out_zxing.equals("未借用")) {
                            tv_czr.setText("借用人");
                            write_zxing.setText("借用");
                            caozuo_zxing = "借用";
                        } else if (in_or_out_zxing.equals("借用")) {
                            tv_czr.setText("归还人");
                            write_zxing.setText("归还");
                            caozuo_zxing = "归还";
                        }
                    }
                    else{
                        Toast.makeText(ZbarActivity.this, "请输入正确的编号！", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        write_zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!write_zxing.getText().toString().equals("请先选择设备")) {
                    if (tv_people.getText().toString().equals("")) {
                        Toast.makeText(ZbarActivity.this, "操作人不能为空！", Toast.LENGTH_LONG).show();
                    } else if (tv_zxing_6.getText().toString().equals("")) {
                        Toast.makeText(ZbarActivity.this, "请选择日期！", Toast.LENGTH_LONG).show();
                    } else {
                        people_zxing = tv_people.getText().toString();
                        trouble_zxing = tv_trouble.getText().toString();
                        date_zxing = tv_zxing_6.getText().toString();
                        ContentValues value_trouble = new ContentValues();
                        value_trouble.put("caozuoren", people_zxing);
                        value_trouble.put("date", date_zxing);
                        value_trouble.put("caozuo", caozuo_zxing);
                        value_trouble.put("xinghao", equipment_type_n_zxing);
                        value_trouble.put("leixing", equipment_type_zxing);
                        value_trouble.put("bianhao", numberFromScan);
                        value_trouble.put("yongtu", trouble_zxing);
                        display_database.insert("troubles", null, value_trouble);
                        value_trouble.clear();
                        String[] args = {String.valueOf(numberFromScan)};


                        if (write_zxing.getText().toString().equals("借用")){
                            ContentValues values = new ContentValues();
                            values.put("in_or_out","借用");
                            display_database.update("armament" ,values , "equipment_number = ?" ,args);
                            Toast.makeText(ZbarActivity.this, "借用成功", Toast.LENGTH_LONG).show();
                        }
                        if (write_zxing.getText().toString().equals("归还")) {
                            ContentValues values = new ContentValues();
                            values.put("in_or_out","未借用");
                            display_database.update("armament" ,values , "equipment_number = ?" ,args);
                            Toast.makeText(ZbarActivity.this, "归还成功", Toast.LENGTH_LONG).show();
                        }
                        tv_test.setText("");
                        tv_zxing_1.setText("");
                        tv_zxing_2.setText("");
                        tv_zxing_3.setText("");
                        tv_zxing_4.setText("");
                        tv_zxing_5.setText("");
                        tv_zxing_6.setText("");
                        tv_people.setText("");
                        tv_trouble.setText("");
                        tv_czr.setText("操作人");
                        write_zxing.setText("请先选择设备");
                    }
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        fab_zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZbarActivity.this, DisplayZxingActivity.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                ZbarActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_zBar);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
// 通过 onActivityResult的方法获取扫描回来的值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this,"内容为空",Toast.LENGTH_LONG).show();
                write_zxing.setText("请先选择设备");
            } else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                EditText tv_return = (EditText) findViewById(R.id.tv_test);
                tv_test.setText(ScanResult);
                numberFromScan = ScanResult;

                /*dbHelper_display = new DBHelper(ZbarActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getWritableDatabase();
                String[] args = {String.valueOf(numberFromScan)};
                Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args);
                if (cursor.moveToFirst()) {
                    equipment_type_n_zxing = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                    equipment_type_zxing = cursor.getString(cursor.getColumnIndex("equipment_type"));
                    airplane_number_zxing = cursor.getString(cursor.getColumnIndex("airplane_number"));
                    equipment_state_zxing = cursor.getString(cursor.getColumnIndex("equipment_state"));
                    in_or_out_zxing = cursor.getString(cursor.getColumnIndex("in_or_out"));
                }
                tv_zxing_1.setText(equipment_type_n_zxing);
                tv_zxing_2.setText(equipment_type_zxing);
                tv_zxing_3.setText(airplane_number_zxing);
                tv_zxing_4.setText(equipment_state_zxing);
                tv_zxing_5.setText(in_or_out_zxing);
                if(in_or_out_zxing.equals("未借用")) {
                    tv_czr.setText("借用人");
                    write_zxing.setText("借用");
                    caozuo_zxing = "借用";
                }else if(in_or_out_zxing.equals("借用")) {
                    tv_czr.setText("归还人");
                    write_zxing.setText("归还");
                    caozuo_zxing = "归还";
                }
*/
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
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
        } else if (id == R.id.nav_slideshow) {
            Intent intent2 = new Intent(ZbarActivity.this, MainActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_manage) {
            Intent intent3 = new Intent(ZbarActivity.this, SecondActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_share) {
            Intent intent4 = new Intent(ZbarActivity.this, InputActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_send) {
            Intent intent5 = new Intent(ZbarActivity.this, TroubleActivity.class);
            startActivity(intent5);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    public void display() {
        tv_zxing_6.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };


}
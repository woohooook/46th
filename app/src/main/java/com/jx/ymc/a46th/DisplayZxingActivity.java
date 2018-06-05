package com.jx.ymc.a46th;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DisplayZxingActivity extends AppCompatActivity {

    private TextView textview_equipment_type;
    private ListView listView;
    private int item_click = 0;
    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private BottomSheetDialog bottomSheet;
    private HashMap<String, String> map;
    int[] trouble_id_1 = new int[10000];
    String[] caozuoren = new String[10000];
    String[] date = new String[10000];
    String[] caozuo = new String[10000];
    String[] xinghao = new String[10000];
    String[] leixing = new String[10000];
    String[] bianhao = new String[10000];
    String[] yongtu = new String[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.display_zxing_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_display_zxing);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle("出入库信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbHelper_display = new DBHelper(this, "info_46h.db", null, 1);
        display_database = dbHelper_display.getWritableDatabase();
        listView = (ListView) findViewById(R.id.listview_zxing);
        Cursor cursor = display_database.rawQuery("select * from troubles order by date", null);
        int amount_zxing = 0;
        if (cursor.moveToFirst()) {
            do {
                int troubleNumber_t = cursor.getInt(cursor.getColumnIndex("trouble_id"));
                trouble_id_1[amount_zxing] = troubleNumber_t;
                String caozuoren_display = cursor.getString(cursor.getColumnIndex("caozuoren"));
                caozuoren[amount_zxing] = caozuoren_display;
                String date_display = cursor.getString(cursor.getColumnIndex("date"));
                date[amount_zxing] = date_display;
                String caozuo_display = cursor.getString(cursor.getColumnIndex("caozuo"));
                caozuo[amount_zxing] = caozuo_display;
                String xinghao_display = cursor.getString(cursor.getColumnIndex("xinghao"));
                xinghao[amount_zxing] = xinghao_display;
                String leixing_display = cursor.getString(cursor.getColumnIndex("leixing"));
                leixing[amount_zxing] = leixing_display;
                String bianhao_display = cursor.getString(cursor.getColumnIndex("bianhao"));
                bianhao[amount_zxing] = bianhao_display;
                String yongtu_display = cursor.getString(cursor.getColumnIndex("yongtu"));
                yongtu[amount_zxing] = yongtu_display;
                amount_zxing++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < amount_zxing; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("trouble_id", trouble_id_1[i]);
            listem.put("date", date[i]);
            listem.put("caozuoren", caozuoren[i]);
            listem.put("caozuo", caozuo[i]);
            listem.put("xinghao", xinghao[i]);
            listem.put("leixing", leixing[i]);
            listem.put("bianhao", bianhao[i]);
            listem.put("yongtu", yongtu[i]);
            listems.add(listem);
        }
        SimpleAdapter simplead = new SimpleAdapter(DisplayZxingActivity.this, listems,
                R.layout.display_item_zxing, new String[]{"date","caozuoren", "caozuo", "xinghao", "leixing", "bianhao", "yongtu"},
                new int[]{R.id.textView_display_zxing_1,R.id.textView_display_zxing_2, R.id.textView_display_zxing_3, R.id.textView_display_zxing_4, R.id.textView_display_zxing_5, R.id.textView_display_zxing_6, R.id.textView_display_zxing_7});
        listView.setAdapter(simplead);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按监听以及底部按钮弹出
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                map = (HashMap<String, String>) parent.getItemAtPosition(position);
                bottomSheet = new BottomSheetDialog(DisplayZxingActivity.this);//实例化
                bottomSheet.setContentView(R.layout.dialog_zxing_bottomsheet);//设置对框框中的布局

            Button button_del = (Button) bottomSheet.findViewById(R.id.button_del);   //删除按钮监听
            button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] args = {String.valueOf(map.get("trouble_id"))};
                String[] argss = {String.valueOf(map.get("bianhao"))};
                display_database.execSQL("delete from troubles where trouble_id = ?",args);
                if (map.get("caozuo").equals("借用")){
                    ContentValues values = new ContentValues();
                    values.put("in_or_out","未借用");
                    display_database.update("armament" ,values , "equipment_number = ?" ,argss);
                }
                if (map.get("caozuo").equals("归还")) {
                    ContentValues values = new ContentValues();
                    values.put("in_or_out","借用");
                    display_database.update("armament" ,values , "equipment_number = ?" ,argss);
                }
                bottomSheet.dismiss();
                Cursor cursor = display_database.rawQuery("select * from troubles order by date", null);
                int amount_zxing = 0;
                if (cursor.moveToFirst()) {
                    do {
                        int troubleNumber_t = cursor.getInt(cursor.getColumnIndex("trouble_id"));
                        trouble_id_1[amount_zxing] = troubleNumber_t;
                        String caozuoren_display = cursor.getString(cursor.getColumnIndex("caozuoren"));
                        caozuoren[amount_zxing] = caozuoren_display;
                        String date_display = cursor.getString(cursor.getColumnIndex("date"));
                        date[amount_zxing] = date_display;
                        String caozuo_display = cursor.getString(cursor.getColumnIndex("caozuo"));
                        caozuo[amount_zxing] = caozuo_display;
                        String xinghao_display = cursor.getString(cursor.getColumnIndex("xinghao"));
                        xinghao[amount_zxing] = xinghao_display;
                        String leixing_display = cursor.getString(cursor.getColumnIndex("leixing"));
                        leixing[amount_zxing] = leixing_display;
                        String bianhao_display = cursor.getString(cursor.getColumnIndex("bianhao"));
                        bianhao[amount_zxing] = bianhao_display;
                        String yongtu_display = cursor.getString(cursor.getColumnIndex("yongtu"));
                        yongtu[amount_zxing] = yongtu_display;
                        amount_zxing++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < amount_zxing; i++) {
                    Map<String, Object> listem = new HashMap<String, Object>();
                    listem.put("trouble_id", trouble_id_1[i]);
                    listem.put("date", date[i]);
                    listem.put("caozuoren", caozuoren[i]);
                    listem.put("caozuo", caozuo[i]);
                    listem.put("xinghao", xinghao[i]);
                    listem.put("leixing", leixing[i]);
                    listem.put("bianhao", bianhao[i]);
                    listem.put("yongtu", yongtu[i]);
                    listems.add(listem);
                }
                SimpleAdapter simplead = new SimpleAdapter(DisplayZxingActivity.this, listems,
                        R.layout.display_item_zxing, new String[]{"date","caozuoren", "caozuo", "xinghao", "leixing", "bianhao", "yongtu"},
                        new int[]{R.id.textView_display_zxing_1,R.id.textView_display_zxing_2, R.id.textView_display_zxing_3, R.id.textView_display_zxing_4, R.id.textView_display_zxing_5, R.id.textView_display_zxing_6, R.id.textView_display_zxing_7});
                listView.setAdapter(simplead);


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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
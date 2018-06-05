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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
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

public class ArmamentSettingActivity extends AppCompatActivity {

    private TextView textView_1_2, textView_2_2, textView_4_2, textView_5_2;
    private Switch control_1_1, control_1_2, control_2_1, control_2_2, control_3, control_4_1, control_4_2, control_5_1, control_5_2;
    private LinearLayout ll_1_1, ll_1_2, ll_2_1, ll_2_2, ll_3, ll_4_1, ll_4_2, ll_5_1, ll_5_2;
    private String[] sInfo_1 = new String[6];
    private String[] sInfo_2 = new String[6];
    private String[] sInfo_3 = new String[6];
    private Spinner spinner1_1, spinner1_2, spinner1_3,spinner1_4, spinner1_5, spinner1_6;
    private Spinner spinner2_1, spinner2_2, spinner2_3,spinner2_4, spinner2_5, spinner2_6;
    private Spinner spinner3_1, spinner3_2, spinner3_3;
    private Spinner spinner4_1, spinner4_2, spinner4_3,spinner4_4, spinner4_5, spinner4_6;
    private Spinner spinner5_1, spinner5_2, spinner5_3,spinner5_4, spinner5_5, spinner5_6;
    private boolean waiguazhuangtai_1_1, waiguazhuangtai_1_2;
    private boolean waiguazhuangtai_2_1, waiguazhuangtai_2_2;
    private boolean waiguazhuangtai_3;
    private boolean waiguazhuangtai_4_1, waiguazhuangtai_4_2;
    private boolean waiguazhuangtai_5_1, waiguazhuangtai_5_2;
    private DBHelper dbHelper_display;
    private SQLiteDatabase display_database;
    private HashMap<String, String> map;
    private com.github.clans.fab.FloatingActionButton fab;
    String number_1_1 = "";
    String number_1_2 = "";
    String number_2_1 = "";
    String number_2_2 = "";
    String number_3 = "";
    String number_4_1 = "";
    String number_4_2 = "";
    String number_5_1 = "";
    String number_5_2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        setContentView(R.layout.arm_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_arm_setting);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar.setTitle(Global.numberOfAirplane);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab_arm_set);

        waiguazhuangtai_1_1 =  false;
        waiguazhuangtai_1_2 =  false;
        waiguazhuangtai_2_1 =  false;
        waiguazhuangtai_2_2 =  false;
        waiguazhuangtai_3 =  false;
        waiguazhuangtai_4_1 =  false;
        waiguazhuangtai_4_2 =  false;
        waiguazhuangtai_5_1 =  false;
        waiguazhuangtai_5_2 =  false;


        if(Global.j8_point_1_1.equals("0")){
            waiguazhuangtai_1_1 = false;
        }
        else{
            waiguazhuangtai_1_1 = true;
        }
        if(Global.j8_point_1_2.equals("0")){
            waiguazhuangtai_1_2 = false;
        }
        else{
            waiguazhuangtai_1_2 = true;
        }
        if(Global.j8_point_2_1.equals("0")){
            waiguazhuangtai_2_1 = false;
        }
        else{
            waiguazhuangtai_2_1 = true;
        }
        if(Global.j8_point_2_2.equals("0")){
            waiguazhuangtai_2_2 = false;
        }
        else{
            waiguazhuangtai_2_2 = true;
        }
        if(Global.j8_point_3.equals("0")){
            waiguazhuangtai_3 = false;
        }
        else{
            waiguazhuangtai_3 = true;
        }
        if(Global.j8_point_4_1.equals("0")){
            waiguazhuangtai_4_1 = false;
        }
        else{
            waiguazhuangtai_4_1 = true;
        }
        if(Global.j8_point_4_2.equals("0")){
            waiguazhuangtai_4_2 = false;
        }
        else{
            waiguazhuangtai_4_2 = true;
        }
        if(Global.j8_point_5_1.equals("0")){
            waiguazhuangtai_5_1 = false;
        }
        else{
            waiguazhuangtai_5_1 = true;
        }
        if(Global.j8_point_5_2.equals("0")){
            waiguazhuangtai_5_2 = false;
        }
        else{
            waiguazhuangtai_5_2 = true;
        }

        spinner1_1 = (Spinner) findViewById(R.id.spinner_set_1_1);
        spinner1_2 = (Spinner) findViewById(R.id.spinner_set_1_2);
        spinner1_3 = (Spinner) findViewById(R.id.spinner_set_1_3);
        spinner1_4 = (Spinner) findViewById(R.id.spinner_set_1_4);
        spinner1_5 = (Spinner) findViewById(R.id.spinner_set_1_5);
        spinner1_6 = (Spinner) findViewById(R.id.spinner_set_1_6);
        textView_1_2 = (TextView) findViewById(R.id.textView_set_1_2);
        control_1_1 = (Switch) findViewById(R.id.control_switch_1_1);
        control_1_2 = (Switch) findViewById(R.id.control_switch_1_2);
        ll_1_1 = (LinearLayout) findViewById(R.id.ll_set_1_1);
        ll_1_2 = (LinearLayout) findViewById(R.id.ll_set_1_2);
        loadLeixing1_1();
        loadXinghao1_1();
        loadLeixing1_2();
        loadXinghao1_2();
        if(waiguazhuangtai_1_1){
            control_1_1.setChecked(true);
            ll_1_1.setVisibility(View.VISIBLE);
            control_1_2.setVisibility(View.VISIBLE);
            textView_1_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_1_2){
            control_1_2.setVisibility(View.VISIBLE);
            textView_1_2.setVisibility(View.VISIBLE);
            control_1_2.setChecked(true);
            ll_1_2.setVisibility(View.VISIBLE);
        }
        if(!waiguazhuangtai_1_2){
            control_1_2.setChecked(false);
        }
        if(waiguazhuangtai_1_1){
            spinner1_1.setSelection(1);
        }
        spinner1_1.setOnItemSelectedListener(new Spinner1ClickListener1());
        spinner1_2.setOnItemSelectedListener(new Spinner1ClickListener2());
        if(waiguazhuangtai_1_2){
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
                    if(waiguazhuangtai_1_2){
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




        spinner2_1 = (Spinner) findViewById(R.id.spinner_set_2_1);
        spinner2_2 = (Spinner) findViewById(R.id.spinner_set_2_2);
        spinner2_3 = (Spinner) findViewById(R.id.spinner_set_2_3);
        spinner2_4 = (Spinner) findViewById(R.id.spinner_set_2_4);
        spinner2_5 = (Spinner) findViewById(R.id.spinner_set_2_5);
        spinner2_6 = (Spinner) findViewById(R.id.spinner_set_2_6);
        textView_2_2 = (TextView) findViewById(R.id.textView_set_2_2);
        control_2_1 = (Switch) findViewById(R.id.control_switch_2_1);
        control_2_2 = (Switch) findViewById(R.id.control_switch_2_2);
        ll_2_1 = (LinearLayout) findViewById(R.id.ll_set_2_1);
        ll_2_2 = (LinearLayout) findViewById(R.id.ll_set_2_2);
        loadLeixing2_1();
        loadXinghao2_1();
        loadLeixing2_2();
        loadXinghao2_2();

        if(waiguazhuangtai_2_1){
            control_2_1.setChecked(true);
            ll_2_1.setVisibility(View.VISIBLE);
            control_2_2.setVisibility(View.VISIBLE);
            textView_2_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_2_2){
            control_2_2.setVisibility(View.VISIBLE);
            textView_2_2.setVisibility(View.VISIBLE);
            control_2_2.setChecked(true);
            ll_2_2.setVisibility(View.VISIBLE);
        }
        if(!waiguazhuangtai_2_2){
            control_2_2.setChecked(false);
        }
        if(waiguazhuangtai_2_1){
            spinner2_1.setSelection(1);
        }
        spinner2_1.setOnItemSelectedListener(new Spinner2ClickListener1());
        spinner2_2.setOnItemSelectedListener(new Spinner2ClickListener2());

        if(waiguazhuangtai_2_2){
            spinner2_4.setSelection(1);
        }
        spinner2_4.setOnItemSelectedListener(new Spinner2ClickListener3());
        spinner2_5.setOnItemSelectedListener(new Spinner2ClickListener4());

        control_2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ll_2_1.setVisibility(View.VISIBLE);
                    textView_2_2.setVisibility(View.VISIBLE);
                    control_2_2.setVisibility(View.VISIBLE);
                    if(waiguazhuangtai_2_2){
                        ll_2_2.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    ll_2_1.setVisibility(View.INVISIBLE);
                    textView_2_2.setVisibility(View.INVISIBLE);
                    control_2_2.setChecked(false);
                    control_2_2.setVisibility(View.INVISIBLE);
                    ll_2_2.setVisibility(View.INVISIBLE);
                }
            }
        });

        control_2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_2_2.setVisibility(View.VISIBLE);
                }
                else{
                    ll_2_2.setVisibility(View.INVISIBLE);
                }
            }
        });



        spinner3_1 = (Spinner) findViewById(R.id.spinner_set_3_1);
        spinner3_2 = (Spinner) findViewById(R.id.spinner_set_3_2);
        spinner3_3 = (Spinner) findViewById(R.id.spinner_set_3_3);
        ll_3 = (LinearLayout) findViewById(R.id.ll_set_3);
        control_3 = (Switch) findViewById(R.id.control_switch_3);
        loadLeixing3();
        loadXinghao3();
        if(waiguazhuangtai_3){
            control_3.setChecked(true);
            ll_3.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_3){
            spinner3_1.setSelection(1);
        }
        spinner3_1.setOnItemSelectedListener(new Spinner3ClickListener1());
        spinner3_2.setOnItemSelectedListener(new Spinner3ClickListener2());

        control_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_3.setVisibility(View.VISIBLE);
                }
                else{
                    ll_3.setVisibility(View.INVISIBLE);
                }
            }
        });

        spinner4_1 = (Spinner) findViewById(R.id.spinner_set_4_1);
        spinner4_2 = (Spinner) findViewById(R.id.spinner_set_4_2);
        spinner4_3 = (Spinner) findViewById(R.id.spinner_set_4_3);
        spinner4_4 = (Spinner) findViewById(R.id.spinner_set_4_4);
        spinner4_5 = (Spinner) findViewById(R.id.spinner_set_4_5);
        spinner4_6 = (Spinner) findViewById(R.id.spinner_set_4_6);
        textView_4_2 = (TextView) findViewById(R.id.textView_set_4_2);
        ll_4_1 = (LinearLayout) findViewById(R.id.ll_set_4_1);
        ll_4_2 = (LinearLayout) findViewById(R.id.ll_set_4_2);
        control_4_1 = (Switch) findViewById(R.id.control_switch_4_1);
        control_4_2 = (Switch) findViewById(R.id.control_switch_4_2);
        loadLeixing4_1();
        loadXinghao4_1();
        loadLeixing4_2();
        loadXinghao4_2();
        if(waiguazhuangtai_4_1){
            control_4_1.setChecked(true);
            ll_4_1.setVisibility(View.VISIBLE);
            control_4_2.setVisibility(View.VISIBLE);
            textView_4_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_4_2){
            control_4_2.setVisibility(View.VISIBLE);
            textView_4_2.setVisibility(View.VISIBLE);
            control_4_2.setChecked(true);
            ll_4_2.setVisibility(View.VISIBLE);
        }
        if(!waiguazhuangtai_4_2){
            control_4_2.setChecked(false);
        }
        if(waiguazhuangtai_4_1){
            spinner4_1.setSelection(1);
        }
        spinner4_1.setOnItemSelectedListener(new Spinner4ClickListener1());
        spinner4_2.setOnItemSelectedListener(new Spinner4ClickListener2());
        if(waiguazhuangtai_4_2){
            spinner4_4.setSelection(1);
        }
        spinner4_4.setOnItemSelectedListener(new Spinner4ClickListener3());
        spinner4_5.setOnItemSelectedListener(new Spinner4ClickListener4());
        control_4_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ll_4_1.setVisibility(View.VISIBLE);
                    textView_4_2.setVisibility(View.VISIBLE);
                    control_4_2.setVisibility(View.VISIBLE);
                    if(waiguazhuangtai_4_2){
                        ll_4_2.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    ll_4_1.setVisibility(View.INVISIBLE);
                    textView_4_2.setVisibility(View.INVISIBLE);
                    control_4_2.setChecked(false);
                    control_4_2.setVisibility(View.INVISIBLE);
                    ll_4_2.setVisibility(View.INVISIBLE);
                }
            }
        });
        control_4_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_4_2.setVisibility(View.VISIBLE);
                }
                else{
                    ll_4_2.setVisibility(View.INVISIBLE);
                }
            }
        });


        spinner5_1 = (Spinner) findViewById(R.id.spinner_set_5_1);
        spinner5_2 = (Spinner) findViewById(R.id.spinner_set_5_2);
        spinner5_3 = (Spinner) findViewById(R.id.spinner_set_5_3);
        spinner5_4 = (Spinner) findViewById(R.id.spinner_set_5_4);
        spinner5_5 = (Spinner) findViewById(R.id.spinner_set_5_5);
        spinner5_6 = (Spinner) findViewById(R.id.spinner_set_5_6);
        textView_5_2 = (TextView) findViewById(R.id.textView_set_5_2);
        ll_5_1 = (LinearLayout) findViewById(R.id.ll_set_5_1);
        ll_5_2 = (LinearLayout) findViewById(R.id.ll_set_5_2);
        control_5_1 = (Switch) findViewById(R.id.control_switch_5_1);
        control_5_2 = (Switch) findViewById(R.id.control_switch_5_2);
        loadLeixing5_1();
        loadXinghao5_1();
        loadLeixing5_2();
        loadXinghao5_2();
        if(waiguazhuangtai_5_1){
            control_5_1.setChecked(true);
            ll_5_1.setVisibility(View.VISIBLE);
            control_5_2.setVisibility(View.VISIBLE);
            textView_5_2.setVisibility(View.VISIBLE);
        }
        if(waiguazhuangtai_5_2){
            control_5_2.setVisibility(View.VISIBLE);
            textView_5_2.setVisibility(View.VISIBLE);
            control_5_2.setChecked(true);
            ll_5_2.setVisibility(View.VISIBLE);
        }
        if(!waiguazhuangtai_5_2){
            control_5_2.setChecked(false);
        }
        if(waiguazhuangtai_5_1){
            spinner5_1.setSelection(1);
        }

        spinner5_1.setOnItemSelectedListener(new Spinner5ClickListener1());
        spinner5_2.setOnItemSelectedListener(new Spinner5ClickListener2());
        if(waiguazhuangtai_5_2){
            spinner5_4.setSelection(1);
        }
        spinner5_4.setOnItemSelectedListener(new Spinner5ClickListener3());
        spinner5_5.setOnItemSelectedListener(new Spinner5ClickListener4());
        control_5_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ll_5_1.setVisibility(View.VISIBLE);
                    textView_5_2.setVisibility(View.VISIBLE);
                    control_5_2.setVisibility(View.VISIBLE);
                    if(waiguazhuangtai_5_2) {
                        ll_5_2.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    ll_5_1.setVisibility(View.INVISIBLE);
                    textView_5_2.setVisibility(View.INVISIBLE);
                    control_5_2.setChecked(false);
                    control_5_2.setVisibility(View.INVISIBLE);
                    ll_5_2.setVisibility(View.INVISIBLE);
                }
            }
        });
        control_5_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ll_5_2.setVisibility(View.VISIBLE);
                }
                else{
                    ll_5_2.setVisibility(View.INVISIBLE);
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
                number_2_1 = spinner2_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2_6.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_2_2 = spinner2_6.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_3 = spinner3_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_4_1 = spinner4_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4_6.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_4_2 = spinner4_6.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner5_3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_5_1 = spinner5_3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner5_6.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number_5_2 = spinner5_6.getSelectedItem().toString();
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
                String point_2_1 = "0";
                if(control_2_1.isChecked()){
                    point_2_1 = "1";
                }
                String point_2_2 = "0";
                if(control_2_2.isChecked()){
                    point_2_2 = "1";
                }
                String point_3 = "0";
                if(control_3.isChecked()){
                    point_3 = "1";
                }
                String point_4_1 = "0";
                if(control_4_1.isChecked()){
                    point_4_1 = "1";
                }
                String point_4_2 = "0";
                if(control_4_2.isChecked()){
                    point_4_2 = "1";
                }
                String point_5_1 = "0";
                if(control_5_1.isChecked()){
                    point_5_1 = "1";
                }
                String point_5_2 = "0";
                if(control_5_2.isChecked()){
                    point_5_2 = "1";
                }


                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
                display_database = dbHelper_display.getWritableDatabase();
                String[] args = {String.valueOf(Global.numberOfAirplane)};
                ContentValues values = new ContentValues();
                values.put("point_1_1",point_1_1);
                values.put("point_1_2",point_1_2);
                values.put("point_2_1",point_2_1);
                values.put("point_2_2",point_2_2);
                values.put("point_3",point_3);
                values.put("point_4_1",point_4_1);
                values.put("point_4_2",point_4_2);
                values.put("point_5_1",point_5_1);
                values.put("point_5_2",point_5_2);
                values.put("number_1_1",number_1_1);
                values.put("number_1_2",number_1_2);
                values.put("number_2_1",number_2_1);
                values.put("number_2_2",number_2_2);
                values.put("number_3",number_3);
                values.put("number_4_1",number_4_1);
                values.put("number_4_2",number_4_2);
                values.put("number_5_1",number_5_1);
                values.put("number_5_2",number_5_2);
                display_database.update("jc8f" ,values , "jc8f_number = ?" ,args);
                Toast.makeText(ArmamentSettingActivity.this, "更新成功！", Toast.LENGTH_LONG).show();
            }
        });


    }


    public void loadLeixing1_1() {
        String[] array1 = new String[]{"请选择", "机翼外侧挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner1_1.setAdapter(adapterOne);
    }

    public void loadXinghao1_1() {
        String[] array2 = new String[]{"请选择", "J8FR-8340-0"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner1_2.setAdapter(modelTwo);
    }
    public void loadLeixing1_2() {
        String[] array1 = new String[]{"请选择", "导发架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner1_4.setAdapter(adapterOne);
    }

    public void loadXinghao1_2() {
        String[] array2 = new String[]{"请选择",  "PF-8A"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner1_5.setAdapter(modelTwo);
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
                if(waiguazhuangtai_1_1){
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

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner1_3.setAdapter(modelThree);
                if(waiguazhuangtai_1_1){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_1_1))
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
            } else {
                spinner1_5.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner1_5.setSelection(0);
                if(waiguazhuangtai_1_2){
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

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner1_6.setAdapter(modelThree);
                if(waiguazhuangtai_1_2){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_1_2))
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

    public void loadLeixing2_1() {
        String[] array1 = new String[]{"请选择", "中间综合挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner2_1.setAdapter(adapterOne);
    }

    public void loadXinghao2_1() {
        String[] array2 = new String[]{"请选择", "J8FR-8330-0"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner2_2.setAdapter(modelTwo);
    }
    public void loadLeixing2_2() {
        String[] array1 = new String[]{"请选择", "导发架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner2_4.setAdapter(adapterOne);
    }

    public void loadXinghao2_2() {
        String[] array2 = new String[]{"请选择", "PF-12", "PF-8A"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner2_5.setAdapter(modelTwo);
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
                if(waiguazhuangtai_2_1){
                    spinner2_2.setSelection(1);
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

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner2_3.setAdapter(modelThree);
                if(waiguazhuangtai_2_1){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_2_1))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner2_3.setSelection(numberForSpinner3);
                }
                sInfo_2[2] = spinner2_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner2ClickListener3 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner2_5.setVisibility(View.INVISIBLE);
                spinner2_6.setVisibility(View.INVISIBLE);
            } else {
                spinner2_5.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner2_5.setSelection(0);
                if(waiguazhuangtai_2_2){
                    dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
                    display_database = dbHelper_display.getReadableDatabase();
                    String[] args = {String.valueOf(Global.j8_number_2_2)};
                    String dataOfType = "";
                    Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args);
                    if (cursor.moveToFirst()) {
                        dataOfType = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                    }
                    cursor.close();
                    if(dataOfType.equals("PF-12")) {
                        spinner2_5.setSelection(1);
                    }
                    else if(dataOfType.equals("PF-8A")){
                        spinner2_5.setSelection(2);
                    }
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner2ClickListener4 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner2_6.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner2_6.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner2_6.setAdapter(modelThree);
                if(waiguazhuangtai_2_2){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_2_2))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner2_6.setSelection(numberForSpinner3);
                }
                sInfo_3[2] = spinner2_6.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }



    public void loadLeixing3() {
        String[] array1 = new String[]{"请选择", "机身挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner3_1.setAdapter(adapterOne);
    }

    public void loadXinghao3() {
        String[] array2 = new String[]{"请选择", "GDJ-IV9", "GDJ-IV1B"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner3_2.setAdapter(modelTwo);
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
                if(waiguazhuangtai_3) {
                    dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
                    display_database = dbHelper_display.getReadableDatabase();
                    String[] args = {String.valueOf(Global.j8_number_3)};
                    String dataOfType = "";
                    Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args);
                    if (cursor.moveToFirst()) {
                        dataOfType = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                    }
                    cursor.close();
                    if (dataOfType.equals("GDJ-IV9")) {
                        spinner3_2.setSelection(1);
                    } else if (dataOfType.equals("GDJ-IV1B")) {
                        spinner3_2.setSelection(2);
                    }
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

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner3_3.setAdapter(modelThree);
                if(waiguazhuangtai_3){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_3))
                            numberForSpinner3 = numberToInit;
                    }
                    spinner3_3.setSelection(numberForSpinner3);
                }
                sInfo_2[3] = spinner3_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void loadLeixing4_1() {
        String[] array1 = new String[]{"请选择", "中间综合挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner4_1.setAdapter(adapterOne);
    }

    public void loadXinghao4_1() {
        String[] array2 = new String[]{"请选择", "J8FR-8330-0"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner4_2.setAdapter(modelTwo);
    }
    public void loadLeixing4_2() {
        String[] array1 = new String[]{"请选择", "导发架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner4_4.setAdapter(adapterOne);
    }

    public void loadXinghao4_2() {
        String[] array2 = new String[]{"请选择", "PF-12", "PF-8A"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner4_5.setAdapter(modelTwo);
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
                if(waiguazhuangtai_4_1){
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

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner4_3.setAdapter(modelThree);
                if(waiguazhuangtai_4_1){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_4_1))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner4_3.setSelection(numberForSpinner3);
                }
                sInfo_2[4] = spinner4_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner4ClickListener3 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner4_5.setVisibility(View.INVISIBLE);
                spinner4_6.setVisibility(View.INVISIBLE);
            } else {
                spinner4_5.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner4_5.setSelection(0);
                if(waiguazhuangtai_4_2){
                    dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
                    display_database = dbHelper_display.getReadableDatabase();
                    String[] args = {String.valueOf(Global.j8_number_4_2)};
                    String dataOfType = "";
                    Cursor cursor = display_database.rawQuery("select * from armament where equipment_number = ?", args);
                    if (cursor.moveToFirst()) {
                        dataOfType = cursor.getString(cursor.getColumnIndex("equipment_type_n"));
                    }
                    cursor.close();
                    if(dataOfType.equals("PF-12")) {
                        spinner4_5.setSelection(1);
                    }
                    else if(dataOfType.equals("PF-8A")){
                        spinner4_5.setSelection(2);
                    }
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner4ClickListener4 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner4_6.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner4_6.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                for (useToCopy = 0; useToCopy < number_set_count; useToCopy++) {
                    array4[useToCopy] = array3[useToCopy];
                }
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner4_6.setAdapter(modelThree);
                if(waiguazhuangtai_4_2){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_4_2))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner4_6.setSelection(numberForSpinner3);
                }
                sInfo_3[4] = spinner4_6.getSelectedItem().toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void loadLeixing5_1() {
        String[] array1 = new String[]{"请选择", "机翼外侧挂架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner5_1.setAdapter(adapterOne);
    }

    public void loadXinghao5_1() {
        String[] array2 = new String[]{"请选择", "J8FR-8340-0"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner5_2.setAdapter(modelTwo);
    }
    public void loadLeixing5_2() {
        String[] array1 = new String[]{"请选择", "导发架"};
        SpinnerAdapter adapterOne = new SpinnerAdapter(this, array1, R.layout.spinner_item);
        spinner5_4.setAdapter(adapterOne);
    }

    public void loadXinghao5_2() {
        String[] array2 = new String[]{"请选择",  "PF-8A"};
        SpinnerAdapter modelTwo = new SpinnerAdapter(this, array2, R.layout.spinner_item);
        spinner5_5.setAdapter(modelTwo);
    }

    public class Spinner5ClickListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner5_2.setVisibility(View.INVISIBLE);
                spinner5_3.setVisibility(View.INVISIBLE);
            } else {
                spinner5_2.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner5_2.setSelection(0);
                if(waiguazhuangtai_5_1){
                    spinner5_2.setSelection(1);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner5ClickListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner5_3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner5_3.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner5_3.setAdapter(modelThree);
                if(waiguazhuangtai_5_1){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_5_1))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner5_3.setSelection(numberForSpinner3);
                }
                sInfo_2[5] = spinner5_3.getSelectedItem().toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner5ClickListener3 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("请选择")) {
                spinner5_5.setVisibility(View.INVISIBLE);
                spinner5_6.setVisibility(View.INVISIBLE);
            } else {
                spinner5_5.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner5_5.setSelection(0);
                if(waiguazhuangtai_5_2){
                    spinner5_5.setSelection(1);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class Spinner5ClickListener4 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("请选择")) {
                spinner5_6.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner5_6.setVisibility(View.VISIBLE);

                dbHelper_display = new DBHelper(ArmamentSettingActivity.this, "info_46h.db", null, 1);
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
                SpinnerAdapter modelThree = new SpinnerAdapter(ArmamentSettingActivity.this, array4, R.layout.spinner_item);
                spinner5_6.setAdapter(modelThree);
                if(waiguazhuangtai_5_2){
                    int numberToInit = 0,numberForSpinner3 = 0;
                    for(numberToInit = 1;numberToInit < number_set_count; numberToInit++) {
                        if (array4[numberToInit].equals(Global.j8_number_5_2))
                            numberForSpinner3 = numberToInit;
                    }

                    spinner5_6.setSelection(numberForSpinner3);
                }
                sInfo_3[5] = spinner5_6.getSelectedItem().toString();
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
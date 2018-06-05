package com.jx.ymc.a46th;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    public static final String groupsSQL = "CREATE TABLE groups(" +
            "group_id varchar(10) primary key  ," +
            "group_name varchar(20))";

    public static final String peoplesSQL = "CREATE TABLE peoples(" +
            "people_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "people_name varchar(20) ," +
            "group_id varchar(10)  ," +
            "group_name varchar(10))" ;

    public static final String troublesSQL = "CREATE TABLE troubles(" +
            "trouble_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "caozuoren varchar(20) ," +
            "date varchar(20)  ," +
            "caozuo varchar(20) ," +
            "xinghao  varchar(20) ,"+
            "leixing  varchar(20) ,"+
            "bianhao  varchar(20) ,"+
            "yongtu varchar(100) )" ;


    public static final String jc8fSQL = "CREATE TABLE jc8f("+
            "jc8f_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "jc8f_number varchar(20) ," +
            "jc8f_type varchar(20) ," +
            "point_1_1 varchar(10)  ," +
            "number_1_1 varchar(20) ," +
            "point_1_2 varchar(10)  ," +
            "number_1_2 varchar(20) ," +
            "point_2_1 varchar(10)  ," +
            "number_2_1 varchar(20) ," +
            "point_2_2 varchar(10)  ," +
            "number_2_2 varchar(20) ," +
            "point_3 varchar(10)  ," +
            "number_3 varchar(20) ," +
            "point_4_1 varchar(10)  ," +
            "number_4_1 varchar(20) ," +
            "point_4_2 varchar(10)  ," +
            "number_4_2 varchar(20) ," +
            "point_5_1 varchar(10)  ," +
            "number_5_1 varchar(20) ," +
            "point_5_2 varchar(10)  ," +
            "number_5_2 varchar(20))" ;

    public static final String j7aSQL = "CREATE TABLE j7a("+
            "j7a_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "j7a_number varchar(20) ," +
            "j7a_type varchar(20) ," +
            "point_1_1 varchar(10)  ," +
            "number_1_1 varchar(20) ," +
            "point_1_2 varchar(10)  ," +
            "number_1_2 varchar(20) ," +
            "point_2 varchar(10)  ," +
            "number_2 varchar(20) ," +
            "point_3_1 varchar(10)  ," +
            "number_3_1 varchar(20) ," +
            "point_3_2 varchar(10)  ," +
            "number_3_2 varchar(20) ," +
            "point_p varchar(10)  ," +
            "number_p varchar(20))" ;

    public static final String armamentSQL = "CREATE TABLE armament("+
            "armament_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "equipment_number varchar(20) ," +  //编号
            "equipment_type varchar(20) ," +    //设备类型
            "equipment_type_n varchar(20) ," +    //设备型号
            "equipment_used_on varchar(10)  ," +    //适用机型 J8-F和J7-A
            "equipment_lifetime varchar(20) ," +  //规定寿命
            "equipment_firstfixtime varchar(10)  ," +  //首翻期
            "fixornot varchar(10) ," +  //是否翻修 是-否
            "bigfix_time varchar(10)  ," +  //翻修/大修时间
            "product_date varchar(20)  ," + //出厂时间
            "used_count varchar(10) ," +    //发射投放次数
            "electric_conformity varchar(10)  ," +  //电气性能是否合格 合格-不合格-不涉及
            "electric_checker varchar(10) ," +  //电气性能检查工作人 若不涉及为 无
            "electric_checkdate varchar(10) ," +  //电气性能检查时间 若不涉及为 无
            "mechanical_conformity varchar(10)  ," +  //机械性能是否合格 合格-不合格-不涉及
            "mechanical_checker varchar(10) ," +  //机械性能检查工作人 若不涉及为 无
            "mechanical_checkdate varchar(10) ," +  //机械性能检查时间 若不涉及为 无
            "seal_conformity varchar(10)  ," +  //密封性能是否合格 合格-不合格-不涉及
            "seal_checker varchar(10) ," +  //密封性能检查工作人 若不涉及为 无
            "seal_checkdate varchar(10) ," +  //密封性能检查时间 若不涉及为 无
            "airplane_number varchar(20)," +    //主机号
            "equipment_state varchar(20)  ," + //设备状态 存放-挂机-大修-返航材
            "in_or_out varchar(10)," +   //借用状态 借用-未借用
            "change_date varchar(20)  ," + //出入库时间
            "change_people varchar(20)," + //最后一次操作人
            "shuoming varchar(200)," + //故障描述
            "fly_count varchar(10)," +    //挂飞次数
            "wulicanshu varchar(100))";   //物理参数

    private Context mContext;

    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context; //未加导致的闪退，原因未明
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(peoplesSQL);
        db.execSQL(troublesSQL);
        db.execSQL(jc8fSQL);
        db.execSQL(j7aSQL);
        db.execSQL(armamentSQL);
        Toast.makeText(mContext, "初始化表成功！", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}


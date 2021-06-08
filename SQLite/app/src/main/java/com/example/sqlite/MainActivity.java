package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText memoEdit;
    TextView result;
    DBHelper dbHelper;

    final static String dbName = "t3.db";
    final static int dbVersion = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = (EditText) findViewById(R.id.nameedit);
        memoEdit = (EditText) findViewById(R.id.memoedit);
        result = (TextView) findViewById(R.id.result);
        dbHelper = new DBHelper(this, dbName, null, dbVersion);
    }

    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);


        switch (v.getId()) {
            case R.id.savebutton:
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"저장되었습니다.",Toast.LENGTH_LONG).show();
                break;
            case R.id.insert: //추가 버튼(insert)
                RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);
                String name = nameEdit.getText().toString();
                String memo = memoEdit.getText().toString();
                int check = rg.getCheckedRadioButtonId();

                db = dbHelper.getWritableDatabase();
                sql = String.format("INSERT INTO t3 VALUES('" + name + "','" + memo + "'," + check + ",'" + getTime + "',0);");

                db.execSQL(sql);
                result.append("\nInsert Success");
                break;

            case R.id.delete: //전체삭제 버튼(delete)
                db = dbHelper.getWritableDatabase();
                sql = "DELETE FROM t3;";
                db.execSQL(sql);
                result.append("\nDelete Success");
                break;


            case R.id.select: //조회 버튼(select)
                db = dbHelper.getReadableDatabase();
                sql = "SELECT * FROM t3;";
                Cursor cursor = db.rawQuery(sql, null);
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        result.append(String.format("\n이름 = %s, 메모 = %s, 우선순위 = %s, 날짜 = %s, finish=%s",
                                cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
                    }
                } else {
                    result.append("\n조회결과가 없습니다.");
                }
                cursor.close();
                break;
        }
        dbHelper.close();
    }

    static class DBHelper extends SQLiteOpenHelper {

        //생성자 - database 파일을 생성한다.
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //DB 처음 만들때 호출. - 테이블 생성 등의 초기 처리.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE t3 (name TEXT, memo TEXT, priority INTEGER, date TEXT, finish INTEGER);");
            //result.append("\nt3 테이블 생성 완료.");
        }

        //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS t3");
            onCreate(db);
        }

    }
}
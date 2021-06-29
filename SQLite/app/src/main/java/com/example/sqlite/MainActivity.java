package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Android에서 데이터베이스를 사용할 때 필요한 API는 android.database.sqlite 패키지로 제공됩니다.
    //원시 SQL 쿼리에 관한 컴파일 시간 확인이 없습니다
    //SQL 쿼리와 데이터 객체 간에 변환하려면 많은 상용구 코드를 사용해야 합니다.
    //https://developer.android.com/training/data-storage/sqlite?hl=ko
    //기기의 내부 저장소에 저장한 파일과 마찬가지로 Android는 데이터베이스를 앱의 비공개 폴더에 저장합니다. 기본적으로 이 공간은 다른 앱이나 사용자가 액세스할 수 없기 때문에 저장된 데이터는 안전하게 유지됩니다.
    //SQLiteOpenHelper 클래스에는 데이터베이스 관리를 위한 유용한 API 세트가 포함되어 있습니다. 이 클래스를 사용하여 데이터베이스의 참조를 가져오면 시스템은 앱이 시작되고 있는 동안이 아닌 필요한 때에만 데이터베이스 생성 및 업데이트와 같이 장시간 실행될 수 있는 작업을 실행합니다. 개발자는 getWritableDatabase() 또는 getReadableDatabase()를 호출하기만 하면 됩니다.
    //이러한 작업은 장시간 실행될 수 있기 때문에 백그라운드 스레드에서 getWritableDatabase() 또는 getReadableDatabase()를 호출해야 합니다. 자세한 내용은 Android의 스레딩을 참조하세요.
    // execSQL 를 이용하여 sqlite 를 많이 이용하는 듯 , 
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
                String memo = memoEdit.getText().toString(); // edit text 인 name 과 memo에
                int check = rg.getCheckedRadioButtonId();

                db = dbHelper.getWritableDatabase();
                sql = String.format("INSERT INTO t3 VALUES('" + name + "','" + memo + "'," + check + ",'" + getTime + "',0);");

                db.execSQL(sql);
                result.append("\nInsert Success" + sql);
                break;

            case R.id.delete: //전체삭제 버튼(delete)
                db = dbHelper.getWritableDatabase();
                sql = "DELETE FROM t3;";
                db.execSQL(sql);
                result.append("\nDelete Success" + sql);
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

    class DBHelper extends SQLiteOpenHelper {

        //생성자 - database 파일을 생성한다.
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //DB 처음 만들때 호출. - 테이블 생성 등의 초기 처리.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE t3 (name TEXT, memo TEXT, priority INTEGER, date TEXT, finish INTEGER);");
            result.append("\nt3 테이블 생성 완료.");
        }

        //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS t3");
            onCreate(db);
        }

    }
    public final class FeedReaderContract {
        // 스키마 및 계약의 정의
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "entry";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        }
    }
    //테이블을 생성하고 삭제하는 일반적인 구문입니다.
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    // SQL Helper를 사용하여 데이터베이스 생성
    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
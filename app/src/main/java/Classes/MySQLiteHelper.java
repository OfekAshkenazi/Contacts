package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ofek on 04-Aug-17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="ContactsDatabase";
    private static final String TABLE_NAME="CONTACTSTABLE";
    private static final String UID="_id";
    private static final String COL_1="NAME";
    private static final String COL_2="SURNAME";
    private static final String COL_3="PHONE";
    private static final int DATABASE_VERSION=1;

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,PHONE INTEGER)");
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);

    }
    public boolean insert(String name,String surname,int phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,surname);
        contentValues.put(COL_3,phone);
        return db.insert(TABLE_NAME,null,contentValues)!=(-1);
    }


}

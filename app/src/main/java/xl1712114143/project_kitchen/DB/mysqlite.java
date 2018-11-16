package xl1712114143.project_kitchen.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class mysqlite extends SQLiteOpenHelper {


    private String sql="create table if not exists userinfo(id varchar(30) primary key,name varchar(30) not null,passwd varchar(50) not null)";
    private String kitchen="create table if not exists kitchen(title varchar(30) primary key,type varchar(30) not null,content varchar(50) not null)";

    public mysqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(kitchen);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}

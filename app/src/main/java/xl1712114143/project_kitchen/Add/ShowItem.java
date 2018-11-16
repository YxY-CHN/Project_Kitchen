package xl1712114143.project_kitchen.Add;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xl1712114143.project_kitchen.DB.mysqlite;

import xl1712114143.project_kitchen.R;

public class ShowItem extends AppCompatActivity {
    private ListView newList;
    private mysqlite dbHelper;
    private ImageView imageView;
    private List<String> contents = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_new);
//        imageView=(ImageView) findViewById(R.id.newImage);
////        Picasso.with(this).load(R.drawable.hualiuyupian).into(imageView);
//        imageView.setImageResource(R.drawable.hualiuyupian);
        dbHelper = new mysqlite(this, "kitchen.db", null, 1);
        newList = (ListView) findViewById(R.id.newList);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(" kitchen", new String[]{"rowid _id", "title","type","content"},null,null,null,null,null);
        SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this, R.layout.new_item, cursor,
                new String[]{"title", "type"}, new int[]{R.id.newtitle, R.id.newtype});

        if (cursor.moveToFirst()){
            do {
                String content=cursor.getString(cursor.getColumnIndex("content"));
                contents.add(content);
            }while(cursor.moveToNext());
        }

        newList.setAdapter(simpleCursorAdapter);
        newList.setOnItemClickListener(new myOnItemClickListener());

    }


    private class myOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(ShowItem.this, ShowDetail.class);
            intent.putExtra("content", contents.get(position));
            startActivity(intent);
        }
    }

//    public boolean showitem(String title, String type,String content) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "select * from kitchen where title=? and type=? and content=?";
//        Cursor cursor = db.rawQuery(sql, new String[]{title, type,content});
//        if (cursor.moveToFirst()) {
//            cursor.close();
//            return true;
//        }
//        return false;
//    }
}

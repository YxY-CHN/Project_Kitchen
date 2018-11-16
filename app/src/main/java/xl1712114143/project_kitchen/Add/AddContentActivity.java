package xl1712114143.project_kitchen.Add;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import xl1712114143.project_kitchen.DB.mysqlite;
import xl1712114143.project_kitchen.MainActivity;
import xl1712114143.project_kitchen.R;

public class AddContentActivity extends AppCompatActivity {

    private mysqlite dbHelper;
    private ImageView upimage;
    private static final int IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_add);
        dbHelper = new mysqlite(this,"kitchen.db",null,1);
        final EditText title=(EditText)findViewById(R.id.maintitle);
        final EditText type=(EditText)findViewById(R.id.type);
        final EditText content=(EditText)findViewById(R.id.contents);
        Button button =  findViewById(R.id.submit);
        Button reset=findViewById(R.id.resetButton);
        ImageView upimage=findViewById(R.id.upImage);
        int image=getIntent().getIntExtra("image",R.drawable.add_image);
        upimage.setImageResource(image);

        upimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String biaoti =title.getText().toString();
                String leibie=type.getText().toString();
                String neirong=content.getText().toString();
                if (biaoti.isEmpty()){
                    Toast.makeText(AddContentActivity.this, "标题不为空！", Toast.LENGTH_SHORT).show();
                }else if (leibie.isEmpty()){
                    Toast.makeText(AddContentActivity.this, "类别不为空！", Toast.LENGTH_SHORT).show();
                }else if (neirong.isEmpty()){
                    Toast.makeText(AddContentActivity.this, "内容不为空！", Toast.LENGTH_SHORT).show();
                }else if (submit(biaoti, leibie,neirong)) {
                    Toast.makeText(AddContentActivity.this, "您的文章已发布！", Toast.LENGTH_SHORT).show();
                    title.setText("");
                    type.setText("");
                    content.setText("");
                    Intent intent=new Intent(AddContentActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                type.setText("");
                content.setText("");
            }
        });
    }



//插入图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ((ImageView)findViewById(R.id.upImage)).setImageBitmap(bm);
    }



    //向数据库插入数据
    public boolean submit(String title,String type,String content){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        /*String sql = "insert into userData(name,password) value(?,?)";
        Object obj[]={username,password};
        db.execSQL(sql,obj);*/
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("type",type);
        values.put("content",content);
        db.insert("kitchen",null,values);
        db.close();
        //db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
        return true;
    }
}

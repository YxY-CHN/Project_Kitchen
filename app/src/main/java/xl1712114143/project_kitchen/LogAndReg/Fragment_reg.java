package xl1712114143.project_kitchen.LogAndReg;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import xl1712114143.project_kitchen.DB.mysqlite;
import xl1712114143.project_kitchen.R;

public class Fragment_reg extends Fragment {
    private mysqlite dbHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new mysqlite(getContext(), "userinfo.db", null, 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View reg = inflater.inflate(R.layout.activity_reg, container, false);

        reg.findViewById(R.id.regButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editText1 = getView().findViewById(R.id.regName);
                EditText editText2 = getView().findViewById(R.id.regNum);
                EditText editText3 = getView().findViewById(R.id.regPasswd);
                String regname = editText1.getText().toString();
                String regnum = editText2.getText().toString();
                String regpasswd = editText3.getText().toString();
                if (regname.isEmpty()){
                    Toast.makeText(getContext(), "用户名不为空！", Toast.LENGTH_SHORT).show();
                } else if (regnum.length()!=11){
                    Toast.makeText(getContext(), "请输入正确的11位手机号！", Toast.LENGTH_SHORT).show();
                } else if (regpasswd.isEmpty()){
                    Toast.makeText(getContext(), "密码不为空！", Toast.LENGTH_SHORT).show();
                }else if (CheckIsDataAlreadyInDBorNot(regnum)) {
                    Toast.makeText(getContext(), "该手机号码已被注册！", Toast.LENGTH_SHORT).show();
                } else if (CheckIsDataAlreadyInDBorNot(regname)){
                    Toast.makeText(getContext(), "该用户名已被注册！", Toast.LENGTH_SHORT).show();
                } else{
                    if (register(regname, regnum,regpasswd)) {
                        Toast.makeText(getContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                    }
                }
            }
        });
        reg.findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = getView().findViewById(R.id.regName);
                EditText editText2 = getView().findViewById(R.id.regNum);
                EditText editText3 = getView().findViewById(R.id.regPasswd);
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });
        return reg;
    }

    //向数据库插入数据
    public boolean register(String name, String id, String passwd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        /*String sql = "insert into userData(name,password) value(?,?)";
        Object obj[]={username,password};
        db.execSQL(sql,obj);*/
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("id", id);
        values.put("passwd", passwd);
        db.insert("userinfo", null, values);
        db.close();
        //db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
        return true;
    }

    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from userinfo where id =?";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}

package xl1712114143.project_kitchen.LogAndReg;

import android.content.Intent;
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
import xl1712114143.project_kitchen.MainActivity;
import xl1712114143.project_kitchen.R;

public class Fragment_log extends Fragment {
    private mysqlite dbHelper;
    private EditText id;
    private EditText passwd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new mysqlite(getContext(), "userinfo.db", null, 1);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View log = inflater.inflate(R.layout.activity_log, container, false);
        log.findViewById(R.id.logButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = getView().findViewById(R.id.logNum);
                EditText passwd = getView().findViewById(R.id.logPasswd);
                String userid = id.getText().toString();
                String userpasswd = passwd.getText().toString();
                if (userid.isEmpty()){
                    Toast.makeText(getContext(), "手机号不为空！", Toast.LENGTH_SHORT).show();
                } else if (userid.length()!=11){
                    Toast.makeText(getContext(), "请输入正确的11位手机号！", Toast.LENGTH_SHORT).show();
                }else if (userpasswd.isEmpty()){
                    Toast.makeText(getContext(), "密码不为空！", Toast.LENGTH_SHORT).show();
                } else
                    if (login(userid, userpasswd)) {
                    Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getContext(),MainActivity.class);
                    id.setText("");
                    passwd.setText("");
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "登录失败，请检查用户名或密码！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        log.findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = getView().findViewById(R.id.logNum);
                passwd = getView().findViewById(R.id.logPasswd);
                id.setText("");
                passwd.setText("");
            }
        });
        return log;
    }

    //验证登录
    public boolean login(String id, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userinfo where id=? and passwd=?";
        Cursor cursor = db.rawQuery(sql, new String[]{id, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
}

package com.thssh.example.sqlorm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.thssh.example.R;
import com.thssh.example.sqlorm.bean.User;
import com.thssh.example.sqlorm.dao.UserDao;
import com.thssh.sqlorm.execption.SqlOrmExecption;
import com.thssh.sqlorm.manager.BaseDaoManager;

import java.util.List;

/**
 * Created by zhangyugehu on 2017/5/10.
 */

public class SqlOrmExampleActivity extends AppCompatActivity {
    private static final String TAG = "SqlOrmExampleActivity";

    private UserDao userDao;
    private EditText etName, etPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlorm_example);
        etName = (EditText) findViewById(R.id.et_name);
        etPass = (EditText) findViewById(R.id.et_pass);
        try {
            userDao = BaseDaoManager.getInstance().getDao(UserDao.class, User.class);
        } catch (SqlOrmExecption e) {
            Log.d(TAG, "onCreate: " + e.toString());
            e.printStackTrace();
        }
    }

    private User createUser() {
        String name = etName.getText().toString();
        String pass = etPass.getText().toString();
        return new User(name, pass);
    }

    private String listArray(List<User> query) {
        StringBuilder builder = new StringBuilder();
        for(User user:query){
            builder.append("name: ").append(user.getName()).append("; password: ").append(user.getPassword()).append("\r\n");
        }
        return builder.toString();
    }

    public void insert(View view) {
        try {
            Toast.makeText(this, "用户已插入 " + userDao.insert(createUser()), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void insertCheck(View view) {
        try {
            User user = createUser();
            if(!userDao.isExists(user, UserDao.Contract.FIELD_NAME)) {
                Toast.makeText(this, "用户已插入 " + userDao.insert(user), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "用户已存在！", Toast.LENGTH_SHORT).show();
            }
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void deleteBean(View view) {
        try {
            Toast.makeText(this, "用户已删除 " + userDao.delete(createUser()), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void deleteBy(View view) {
        try {
            Toast.makeText(this, "用户已删除 " + userDao.delete(createUser(), UserDao.Contract.FIELD_NAME), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void deleteAll(View view) {
        try {
            Toast.makeText(this, "用户已删除 " + userDao.clear(), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void drop(View view) {
        try {
            userDao.drop();
            Toast.makeText(this, "用户表已删除", Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void updateBy(View view) {
        try {
            Toast.makeText(this, "用户已更新 " + userDao.update(createUser(), UserDao.Contract.FIELD_NAME), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void queryAll(View view) {
        try {
            List list = userDao.newQueryBuilder()
                    .entity(createUser())
                    .query();
            Toast.makeText(this, "查找结果\r\n" + listArray(list), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }

    public void queryByName(View view) {
        try {
            List list = userDao.newQueryBuilder()
                    .entity(createUser())
                    .bys(UserDao.Contract.FIELD_NAME)
                    .query();
            Toast.makeText(this, "查找结果\r\n" + listArray(list), Toast.LENGTH_SHORT).show();
        } catch (SqlOrmExecption sqlOrmExecption) {
            sqlOrmExecption.printStackTrace();
        }
    }
}

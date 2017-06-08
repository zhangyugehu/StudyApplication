package com.thssh.example.sqlorm.bean;

import com.thssh.example.sqlorm.dao.UserDao;
import com.thssh.sqlorm.annotation.DBField;
import com.thssh.sqlorm.annotation.DBTable;

/**
 * Created by zhangyugehu on 2017/5/10.
 */

@DBTable(UserDao.Contract.TB_NAME)
public class User {
    @DBField(UserDao.Contract.FIELD_NAME)
    public String name;
    @DBField(UserDao.Contract.FIELD_PASSWORD)
    public String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.thssh.example.sqlorm.dao;

import com.thssh.example.sqlorm.bean.User;
import com.thssh.sqlorm.AbsBaseDao;

/**
 * Created by zhangyugehu on 2017/5/10.
 */

public class UserDao extends AbsBaseDao<User> {

    public interface Contract{
        String TB_NAME = "tb_user";
        String FIELD_NAME = "tb_name";
        String FIELD_PASSWORD = "tb_password";
    }

    @Override
    protected String createTable() {
        return "CREATE TABLE " +
                "IF NOT EXISTS " +
                Contract.TB_NAME +
                " ( " +
                Contract.FIELD_NAME + " varchar(20), " +
                Contract.FIELD_PASSWORD + " varchar(20) " +
                ")";
    }
}

package com.thssh.sqlorm.manager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.thssh.sqlorm.AbsBaseDao;
import com.thssh.sqlorm.execption.SqlOrmExecption;

import java.io.File;

/**
 * Created by zhangyugehu on 2017/5/10.
 */

public class BaseDaoManager {
    private SQLiteDatabase sqLiteDatabase;
    private static final BaseDaoManager ourInstance = new BaseDaoManager();

    public static BaseDaoManager getInstance() {
        return ourInstance;
    }

    private BaseDaoManager() {
        File dbFile = new File(Environment.getExternalStorageDirectory(), "thssh.db");
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }

    public synchronized <T extends AbsBaseDao<M>, M> T getDao(Class<T> daoClass, Class<M> entityClass) throws SqlOrmExecption {
        T baseDao = null;
        try{
            baseDao = daoClass.newInstance();
            baseDao.init(sqLiteDatabase, entityClass);
        } catch (Throwable e) {
            throw new SqlOrmExecption(e);
        }
        return baseDao;
    }
}

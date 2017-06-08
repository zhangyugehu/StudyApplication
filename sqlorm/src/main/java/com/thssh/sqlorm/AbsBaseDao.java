package com.thssh.sqlorm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.thssh.sqlorm.annotation.DBField;
import com.thssh.sqlorm.annotation.DBTable;
import com.thssh.sqlorm.execption.SqlOrmExecption;
import com.thssh.sqlorm.util.SqlUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据库实体类继承自该类<p/>
 *
 * Created by zhangyugehu on 2017/5/10.
 */

public abstract class AbsBaseDao<T> implements IBaseDao<T> {

    private final String[] EMPTY_ARRAY = new String[0];

    private SQLiteDatabase mSqLiteDatabase;
    /** User 的 class类型 */
    private Class mEntityClass;
    /** 防止多次初始化 */
    private boolean init;
    private String mTableName;

    /** 表字段[tb_name]- 对象字段[name] 映射表 */
    private HashMap<String, Field> mCacheMap;

    public void init(SQLiteDatabase sqLiteDatabase, Class entityClass) throws SqlOrmExecption {
        this.mSqLiteDatabase = sqLiteDatabase;
        this.mEntityClass = entityClass;
        if(!init){
            if(!sqLiteDatabase.isOpen()) {throw new SqlOrmExecption("database is not open");}
            String sqlCreate = createTable();
            Log.d("thssh", "sqlCreate: " + sqlCreate);
            sqLiteDatabase.execSQL(sqlCreate);
            DBTable dbTable = (DBTable) entityClass.getAnnotation(DBTable.class);
            if(dbTable == null) {throw new SqlOrmExecption("db table is null");}
            mTableName = dbTable.value();
            initCacheMap();
            init = true;
        }
    }

    /**
     * 建表语句
     * @return
     */
    protected abstract String createTable();

    @Override
    public long insert(T entity) throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        long insert = -1;
        try {
            insert = mSqLiteDatabase.insert(mTableName, null, convertContentValue(entity));
        }catch (Exception e){}
        return insert;
    }

    @Override
    public int delete(T where) throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        int delete = -1;
        delete = mSqLiteDatabase.delete(mTableName, buildSelection(), buildAllSelectionArgs(where));
        return delete;
    }

    @Override
    public int delete(T entity, String... bys) throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        int delete = -1;
        delete = mSqLiteDatabase.delete(mTableName, buildSelection(bys), buildSelectionArgs(entity, bys));
        return delete;
    }

    @Override
    public int clear() throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        int delete = -1;
        delete = mSqLiteDatabase.delete(mTableName, "1=?", new String[]{"1"});
        return delete;
    }

    @Override
    public void drop() throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        String dropSql = "DROP TABLE IF EXISTS " + mTableName;
        mSqLiteDatabase.execSQL(dropSql);
    }

    @Override
    public int update(T entity, T where) throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        int update = -1;
        try{
            update = mSqLiteDatabase.update(mTableName, convertContentValue(entity), buildSelection(), buildAllSelectionArgs(where));
        }catch (Exception e){}
        return update;
    }

    @Override
    public int update(T entity, String... bys) throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        int update = -1;
        try{
            update = mSqLiteDatabase.update(mTableName, convertContentValue(entity), buildSelection(bys), buildSelectionArgs(entity, bys));
        }catch (Exception e){}
        return update;
    }

    @Override
    public List<T> query(T where, QueryBuilder builder) throws SqlOrmExecption {
        if(!mSqLiteDatabase.isOpen()){throw new SqlOrmExecption("database is not open");}
        if(where == null){throw new SqlOrmExecption("query entity can not be null");}
        Cursor cursor = mSqLiteDatabase.query(mTableName, buildAllColums(), buildSelection(builder.bys), buildSelectionArgs(where, builder.bys), builder.groupBy, builder.having, builder.orderBy);
        List<T> data = new LinkedList<>();
        while(cursor.moveToNext()){
            T bean = buildBean(where.getClass(), cursor);
            data.add(bean);
        }
        return data;
    }

    public QueryBuilder<T> newQueryBuilder(){
        return new QueryBuilder().dao(this);
    }

    /**
     *
     * @param entity
     * @return
     */
    public boolean isExists(T entity, String... bys){
        Cursor cursor = mSqLiteDatabase.query(mTableName, buildAllColums(), buildSelection(bys), buildSelectionArgs(entity, bys), null, null, null);
        return cursor.moveToNext();
    }

    /**
     * 初始化表中字段和对象字段的映射关系
     */
    private void initCacheMap() {
        if(mCacheMap == null) {mCacheMap = new HashMap<>();}
        // 查表
        String sql = "SELECT * FROM " + mTableName + " LIMIT 1, 0";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        String[] columnNames = cursor.getColumnNames();
        Field[] fields = mEntityClass.getDeclaredFields();

        for(String columnName : columnNames){
            String fieldAnnotationName = null;
            Field columnField = null;
            for(Field field : fields){
                DBField fieldAnnotation = field.getAnnotation(DBField.class);
                if(fieldAnnotation != null){
                    fieldAnnotationName = fieldAnnotation.value();
                    if(TextUtils.equals(fieldAnnotationName, columnName)){
                        columnField = field;
                        break;
                    }
                }
            }
            if(columnField != null){
                mCacheMap.put(columnName, columnField);
            }
        }
    }

    private ContentValues convertContentValue(T t) {
        HashMap<String, String> result = getValues(t);
        ContentValues cv = SqlUtils.convertMap(result);
        return cv;
    }

    /**
     * 将mCacheMap中存储的 表字段[tb_name]- 对象字段[name] 转化成 表字段[tb_name]- 对象值["lisi"]
     * @param t
     * @return
     */
    private HashMap<String, String> getValues(T t) {
        HashMap<String, String> values = new HashMap<>();
        Iterator<Field> iterator = mCacheMap.values().iterator();
        try {
            while (iterator.hasNext()) {
                Field field = iterator.next();
                String cacheKey = null;
                String cacheValue = null;
                DBField annotation = field.getAnnotation(DBField.class);
                if (annotation != null) {
                    cacheKey = annotation.value();
                    if (field.get(t) == null) {
                        continue;
                    }
                    cacheValue = field.get(t).toString();
                    if(cacheKey != null){
                        values.put(cacheKey, cacheValue);
                    }
                }
            }
        }catch (Exception e){
            return values;
        }
        return values;
    }

    private T buildBean(Class clazz, Cursor cursor) throws SqlOrmExecption {
        try {
            Iterator<String> iterator = mCacheMap.keySet().iterator();
            T bean = (T) clazz.newInstance();
            while (iterator.hasNext()){
                String columnName = iterator.next();
                String value = cursor.getString(cursor.getColumnIndex(columnName));
                Field field = mCacheMap.get(columnName);
                field.setAccessible(true);
                field.set(bean, value);
            }
            return bean;
        }catch (Exception e){
            throw new SqlOrmExecption(e);
        }
    }

    private String[] buildSelectionArgs(T entity, String... bys) {
        if(entity == null) return null;
        if(bys == null || bys.length <=0) return null;
        List<String> args = new LinkedList<>();
        for(String by : bys){
            Field field = mCacheMap.get(by);
            field.setAccessible(true);
            try {
                String value = (String) field.get(entity);
                args.add(value);
            } catch (IllegalAccessException e) {
                continue;
            }
        }
        return args.toArray(EMPTY_ARRAY);
    }

    private String buildSelection(String[] bys) {
        if(bys == null || bys.length <=0) return null;
        StringBuilder builder = new StringBuilder();
        for(String by : bys){
            builder.append(by).append("=").append("? AND ");
        }
        return builder.subSequence(0, builder.lastIndexOf(" AND ")).toString();
    }

    private String[] buildAllColums() {
        List<String> columnList = new LinkedList<>();
        Iterator<String> iterator = mCacheMap.keySet().iterator();
        while (iterator.hasNext()){
            String column = iterator.next();
            if(column != null) columnList.add(column);
        }
        return columnList.toArray(EMPTY_ARRAY);
    }

    /**
     * 列出全部字段匹配
     * @param where
     * @return
     */
    private String[] buildAllSelectionArgs(T where) {
        List<String> argList = new LinkedList<>();
        Iterator<Field> iterator = mCacheMap.values().iterator();
        while (iterator.hasNext()){
            Field field = iterator.next();
            field.setAccessible(true);
            try {
                String value = (String) field.get(where);
                argList.add(value);
            } catch (IllegalAccessException e) {
                continue;
            }
        }
        String[] args = argList.toArray(EMPTY_ARRAY);
        return args;
    }

    private String buildSelection() {
        if(mCacheMap == null) return null;
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = mCacheMap.keySet().iterator();
        while (iterator.hasNext()){
            String tb_field = iterator.next();
            builder.append(tb_field).append("=").append("? AND ");
        }
        return builder.subSequence(0, builder.lastIndexOf(" AND ")).toString();
    }
}

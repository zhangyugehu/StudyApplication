package com.thssh.sqlorm.util;

import android.content.ContentValues;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhangyugehu on 2017/5/10.
 */

public class SqlUtils {
    public static ContentValues convertMap(Map<String, String> map){
        ContentValues cv = new ContentValues();
        if(map == null){return cv;}
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            cv.put(key, value);
        }
        return cv;
    }
}

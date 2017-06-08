package com.thssh.sqlorm.execption;

import android.text.TextUtils;

/**
 * Created by zhangyugehu on 2017/5/10.
 */

public class SqlOrmExecption extends Throwable {

    private String message;
    public SqlOrmExecption(String message) {
        super();
        this.message = message;
    }

    public SqlOrmExecption(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return TextUtils.isEmpty(message)?super.toString(): "SqlOrmExecption: " + message;
    }
}

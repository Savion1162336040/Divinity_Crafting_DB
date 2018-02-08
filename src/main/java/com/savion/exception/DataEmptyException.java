package com.savion.exception;

/**
 * Created by savion on 2018/2/8.
 * 数据集空异常
 */
public class DataEmptyException extends RuntimeException {
    public DataEmptyException() {
        this("data empty!");
    }

    public DataEmptyException(String msg) {
        super(msg);
    }
}

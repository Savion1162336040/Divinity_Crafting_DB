package com.savion.exception;

/**
 * Created by savion on 2018/2/8.
 * 数据解析错误异常
 */
public class DataParseException extends RuntimeException {
    public DataParseException(){
        this("data parse error!");
    }
    public DataParseException(String msg){
        super(msg);
    }
}

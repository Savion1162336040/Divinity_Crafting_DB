package com.savion.main;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;

/**
 * Created by savion on 2018/2/8.
 */
public class MainModule {
    @At("/hello")
    @Ok("jsp:jsp.hello")
    public String doHello(){
        return "hello nuzt";
    }
}

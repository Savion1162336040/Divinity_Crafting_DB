package com.savion.main;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by savion on 2018/2/8.
 */
@Modules(scanPackage = true)
@SetupBy(value = MainSetup.class)
@IocBy(type = ComboIocProvider.class, args = {"*js", "ioc/",
        // 这个package下所有带@IocBean注解的类,都会登记上
        "*anno", "com.savion.bean",
        "*tx", // 事务拦截 aop
        "*async"}) // 异步执行aop
public class MainModule {
    @At
    public String doHello() {
        return "hello nuzt";
    }
}

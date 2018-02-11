package com.savion;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by savion on 2018/2/8.
 */
@Modules(scanPackage = true)
@SetupBy(value = MainSetup.class)
@IocBy(type = ComboIocProvider.class, args = {"*js", "ioc/",
        //*开头一般为loader的类型，具体对应关系在ComboIocLoader中有对应关系
        //处理规律, 当遇到第一种参数(*),则认为接下来的一个或多个参数为这一个IocLoader的参数,直至遇到另外一个*开头的参数
        //ioc路径为资源路径,idea一般在resources路径下
        // 这个package下所有带@IocBean注解的类,都会登记上
        "*anno", "com.savion.module",
        "*tx", // 事务拦截 aop
        "*async"}) // 异步执行aop
public class MainModule {
}

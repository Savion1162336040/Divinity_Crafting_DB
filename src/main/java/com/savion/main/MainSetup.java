package com.savion.main;

import com.savion.bean.User;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.text.SimpleDateFormat;

/**
 * Created by savion on 2018/2/9.
 */
public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nutConfig) {
        Ioc ioc = nutConfig.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao,"com.savion.bean",false);
        if (dao.count(User.class)==0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(System.currentTimeMillis());
            User user = new User();
            user.setName("savion");
            user.setPassword("sw820820");
            user.setCreateTime(date);
            user.setUpdateTime(date);
            dao.insert(user);
            System.out.println("create default account : savion");
        }
    }

    @Override
    public void destroy(NutConfig nutConfig) {
    }
}

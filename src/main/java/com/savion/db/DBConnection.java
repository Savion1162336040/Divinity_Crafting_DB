package com.savion.db;

import com.savion.bean.Weapon;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 */
public class DBConnection {

    private static DBConnection instance;
    private SimpleDataSource simpleDataSource;
    private DBConnection() {
        getSource();
    }

    private void getSource(){
        simpleDataSource = new SimpleDataSource();
        simpleDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/divinity");
        simpleDataSource.setUsername("savion");
        simpleDataSource.setPassword("sw820820");
    }

    public static DBConnection getInstance() {
        synchronized (DBConnection.class) {
            if (instance == null)
                instance = new DBConnection();
            return instance;
        }
    }

    private void checkConnection(){
        if (simpleDataSource==null){
            getSource();
        }
    }

    public SimpleDataSource getSimpleDataSource() {
        return simpleDataSource;
    }
}

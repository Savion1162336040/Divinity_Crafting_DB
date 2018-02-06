package com.savion.db;


import com.savion.bean.Weapon;
import com.savion.behavior.DBOperation;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 */
public class WeaponDao implements DBOperation<Weapon> {
    private DBConnection connection;
    private Dao dao;

    public WeaponDao() {
        connection = DBConnection.getInstance();
        dao = new NutDao(connection.getSimpleDataSource());
        dao.create(Weapon.class, false);
    }

    public List<Weapon> queryAll() {
        return dao.query(Weapon.class, null);
    }

    public boolean dropTable() {
        return dao.drop(Weapon.class);
    }

    public boolean insert(Weapon weapon) {
        Weapon weapon1 = dao.insert(weapon);
        return weapon1!=null?true:false;
    }

    public boolean update(Weapon weapon) {
        int weapon1 = dao.update(weapon);
        return weapon1>0?true:false;
    }

    public boolean delete(Weapon weapon) {
        return false;
    }

    public boolean delete(String name) {
        return false;
    }

    public Weapon query() {
        return null;
    }
}

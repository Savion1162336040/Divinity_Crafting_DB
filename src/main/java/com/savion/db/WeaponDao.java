package com.savion.db;


import com.savion.bean.Weapon;
import com.savion.behavior.DBOperation;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 * Weapon表Dao
 */
public class WeaponDao implements DBOperation<Weapon> {
    private DBConnection connection;
    private Dao dao;

    public WeaponDao() {
        connection = DBConnection.getInstance();
        dao = new NutDao(connection.getSimpleDataSource());
        dao.create(Weapon.class, false);
    }

    /**
     * 删除表
     * @return 是否成功
     */
    public boolean dropTable() {
        return dao.drop(Weapon.class);
    }

    /**
     * fetch指定id的表记录
     * @param id string主键或@Name或唯一索引字段
     * @return
     */
    public Weapon fetch(String id) {
        return dao.fetch(Weapon.class,id);
    }

    /**
     * 插入指定表记录
     * @param weapon 指定表记录
     * @return 是否成功
     */
    public boolean insert(Weapon weapon) {
        Weapon weapon1 = dao.insert(weapon);
        return weapon1!=null?true:false;
    }

    /**
     * 更新指定表记录
     * @param weapon
     * @return
     */
    public boolean update(Weapon weapon) {
        int weapon1 = dao.update(weapon);
        return weapon1>0?true:false;
    }

    /**
     * 按条件更新指定表记录
     * @param weapon
     * @param pattern 条件正则表达式指定更新的字段匹配值
     * @return
     */
    public boolean update(Weapon weapon, String pattern) {
        int c = dao.update(weapon,pattern);
        return c>0?true:false;
    }

    /**
     * 更新表记录集合
     * @param weapons
     * @return
     */
    public boolean update(List<Weapon> weapons) {
        int c = dao.update(weapons);
        return c>0?true:false;
    }

    /**
     * 按条件更新记录集合
     * @param weapons
     * @param pattern 条件正则表达式指定更新字段
     * @return
     */
    public boolean update(List<Weapon> weapons, String pattern) {
        int c = dao.update(weapons,pattern);
        return c>0?true:false;
    }

    /**
     * 删除指定表记录
     * @param weapon
     * @return
     */
    public boolean delete(Weapon weapon) {
        int c = dao.delete(weapon);
        return c>0?true:false;
    }

    /**
     * 清空表
     * @return
     */
    public boolean clear() {
        int c = dao.clear(Weapon.class);
        return c>0?true:false;
    }

    /**
     * 按条件删除表记录
     * @param cnd 指定条件
     * @return
     */
    public boolean clear(Condition cnd) {
        int c = dao.clear(Weapon.class,cnd);
        return c>0?true:false;
    }

    /**
     * 删除指定namr的记录(表结构需要指定@Name)
     * @param name 指定删除name
     * @return
     */
    public boolean delete(String name) {
        int c = dao.delete(Weapon.class,name);
        return c>0?true:false;
    }

    /**
     * 删除指定id的记录（表结构中需要指定@ID）
     * @param id 指定ID
     * @return
     */
    public boolean delete(int id) {
        int c = dao.delete(Weapon.class,id);
        return c>0?true:false;
    }

    /**
     * 查询所有
     * @return
     */
    public List<Weapon> queryAll() {
        return query(null);
    }
    /**
     * 按条件查询
     * @param cnd 条件
     * @return
     */
    public List<Weapon> query(Condition cnd) {
        return query(cnd,-1,-1);
    }
    /**
     * 分页查询
     * @param cnd 条件
     * @param pIndex 第几页
     * @param pageCount 每页多少条记录
     * @return
     */
    public List<Weapon> query(Condition cnd, int pIndex, int pageCount) {
        if (pIndex<0||pageCount<0){
            return dao.query(Weapon.class,cnd);
        }
        return dao.query(Weapon.class,cnd, dao.createPager(pIndex,pageCount));
    }
}

package com.savion.db;

import com.savion.bean.Potion;
import com.savion.bean.Potion;
import com.savion.behavior.DBOperation;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import java.util.List;

/**
 * Created by savion on 2018/2/7.
 */
public class PotionDao implements DBOperation<Potion> {
    private DBConnection connection;
    private Dao dao;

    public PotionDao() {
        connection = DBConnection.getInstance();
        dao = new NutDao(connection.getSimpleDataSource());
        dao.create(Potion.class, false);
    }

    /**
     * 删除表
     * @return 是否成功
     */
    public boolean dropTable() {
        return dao.drop(Potion.class);
    }

    /**
     * fetch指定id的表记录
     * @param id string主键或@Name或唯一索引字段
     * @return
     */
    public Potion fetch(String id) {
        return dao.fetch(Potion.class,id);
    }

    /**
     * 插入指定表记录
     * @param Potion 指定表记录
     * @return 是否成功
     */
    public boolean insert(Potion Potion) {
        Potion Potion1 = dao.insert(Potion);
        return Potion1!=null?true:false;
    }

    /**
     * 更新指定表记录
     * @param Potion
     * @return
     */
    public boolean update(Potion Potion) {
        int Potion1 = dao.update(Potion);
        return Potion1>0?true:false;
    }

    /**
     * 按条件更新指定表记录
     * @param Potion
     * @param pattern 条件正则表达式指定更新的字段匹配值
     * @return
     */
    public boolean update(Potion Potion, String pattern) {
        int c = dao.update(Potion,pattern);
        return c>0?true:false;
    }

    /**
     * 更新表记录集合
     * @param Potions
     * @return
     */
    public boolean update(List<Potion> Potions) {
        int c = dao.update(Potions);
        return c>0?true:false;
    }

    /**
     * 按条件更新记录集合
     * @param Potions
     * @param pattern 条件正则表达式指定更新字段
     * @return
     */
    public boolean update(List<Potion> Potions, String pattern) {
        int c = dao.update(Potions,pattern);
        return c>0?true:false;
    }

    /**
     * 删除指定表记录
     * @param Potion
     * @return
     */
    public boolean delete(Potion Potion) {
        int c = dao.delete(Potion);
        return c>0?true:false;
    }

    /**
     * 清空表
     * @return
     */
    public boolean clear() {
        int c = dao.clear(Potion.class);
        return c>0?true:false;
    }

    /**
     * 按条件删除表记录
     * @param cnd 指定条件
     * @return
     */
    public boolean clear(Condition cnd) {
        int c = dao.clear(Potion.class,cnd);
        return c>0?true:false;
    }

    /**
     * 删除指定namr的记录(表结构需要指定@Name)
     * @param name 指定删除name
     * @return
     */
    public boolean delete(String name) {
        int c = dao.delete(Potion.class,name);
        return c>0?true:false;
    }

    /**
     * 删除指定id的记录（表结构中需要指定@ID）
     * @param id 指定ID
     * @return
     */
    public boolean delete(int id) {
        int c = dao.delete(Potion.class,id);
        return c>0?true:false;
    }

    /**
     * 查询所有
     * @return
     */
    public List<Potion> queryAll() {
        return query(null);
    }
    /**
     * 按条件查询
     * @param cnd 条件
     * @return
     */
    public List<Potion> query(Condition cnd) {
        return query(cnd,-1,-1);
    }
    /**
     * 分页查询
     * @param cnd 条件
     * @param pIndex 第几页
     * @param pageCount 每页多少条记录
     * @return
     */
    public List<Potion> query(Condition cnd, int pIndex, int pageCount) {
        if (pIndex<0||pageCount<0){
            return dao.query(Potion.class,cnd);
        }
        return dao.query(Potion.class,cnd, dao.createPager(pIndex,pageCount));
    }
}

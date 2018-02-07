package com.savion.db;

import com.savion.bean.Material;
import com.savion.bean.Material;
import com.savion.behavior.DBOperation;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import java.util.List;

/**
 * Created by savion on 2018/2/7.
 */
public class MaterialDao implements DBOperation<Material>{
    private DBConnection connection;
    private Dao dao;

    public MaterialDao() {
        connection = DBConnection.getInstance();
        dao = new NutDao(connection.getSimpleDataSource());
        dao.create(Material.class, false);
    }

    /**
     * 删除表
     * @return 是否成功
     */
    public boolean dropTable() {
        return dao.drop(Material.class);
    }

    /**
     * fetch指定id的表记录
     * @param id string主键或@Name或唯一索引字段
     * @return
     */
    public Material fetch(String id) {
        return dao.fetch(Material.class,id);
    }

    /**
     * 插入指定表记录
     * @param Material 指定表记录
     * @return 是否成功
     */
    public boolean insert(Material Material) {
        Material Material1 = dao.insert(Material);
        return Material1!=null?true:false;
    }

    /**
     * 更新指定表记录
     * @param Material
     * @return
     */
    public boolean update(Material Material) {
        int Material1 = dao.update(Material);
        return Material1>0?true:false;
    }

    /**
     * 按条件更新指定表记录
     * @param Material
     * @param pattern 条件正则表达式指定更新的字段匹配值
     * @return
     */
    public boolean update(Material Material, String pattern) {
        int c = dao.update(Material,pattern);
        return c>0?true:false;
    }

    /**
     * 更新表记录集合
     * @param Materials
     * @return
     */
    public boolean update(List<Material> Materials) {
        int c = dao.update(Materials);
        return c>0?true:false;
    }

    /**
     * 按条件更新记录集合
     * @param Materials
     * @param pattern 条件正则表达式指定更新字段
     * @return
     */
    public boolean update(List<Material> Materials, String pattern) {
        int c = dao.update(Materials,pattern);
        return c>0?true:false;
    }

    /**
     * 删除指定表记录
     * @param Material
     * @return
     */
    public boolean delete(Material Material) {
        int c = dao.delete(Material);
        return c>0?true:false;
    }

    /**
     * 清空表
     * @return
     */
    public boolean clear() {
        int c = dao.clear(Material.class);
        return c>0?true:false;
    }

    /**
     * 按条件删除表记录
     * @param cnd 指定条件
     * @return
     */
    public boolean clear(Condition cnd) {
        int c = dao.clear(Material.class,cnd);
        return c>0?true:false;
    }

    /**
     * 删除指定namr的记录(表结构需要指定@Name)
     * @param name 指定删除name
     * @return
     */
    public boolean delete(String name) {
        int c = dao.delete(Material.class,name);
        return c>0?true:false;
    }

    /**
     * 删除指定id的记录（表结构中需要指定@ID）
     * @param id 指定ID
     * @return
     */
    public boolean delete(int id) {
        int c = dao.delete(Material.class,id);
        return c>0?true:false;
    }

    /**
     * 查询所有
     * @return
     */
    public List<Material> queryAll() {
        return query(null);
    }
    /**
     * 按条件查询
     * @param cnd 条件
     * @return
     */
    public List<Material> query(Condition cnd) {
        return query(cnd,-1,-1);
    }
    /**
     * 分页查询
     * @param cnd 条件
     * @param pIndex 第几页
     * @param pageCount 每页多少条记录
     * @return
     */
    public List<Material> query(Condition cnd, int pIndex, int pageCount) {
        if (pIndex<0||pageCount<0){
            return dao.query(Material.class,cnd);
        }
        return dao.query(Material.class,cnd, dao.createPager(pIndex,pageCount));
    }
}

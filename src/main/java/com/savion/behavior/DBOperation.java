package com.savion.behavior;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 */
public interface DBOperation<E> {
    /**
     * 删除表
     * @return 是否删除成功
     */
    boolean dropTable();
    /**
     * 获取fetch(如果表有声明@Name,或有String型主键，或带唯一性索引的字段)
     * @param id string主键或@Name或唯一索引字段
     * @return 匹配到的表记录
     */
    E fetch(String id);
    /**
     * 插入指定记录
     * @param e 指定表记录
     * @return 是否插入成功
     */
    boolean insert(E e);
    /**
     * 更新指定记录
     * @param e 指定表记录
     * @return 是否更新成功
     */
    boolean update(E e);

    /**
     * 按条件更新(parrtern为正则表达式-指定更新的字段,如:^name$仅更新name字段)
     * @param e 指定表记录
     * @param pattern 条件正则表达式
     * @return 是否更新成功
     */
    boolean update(E e,String pattern);
    /**
     * 更新集合
     * @param es 指定表记录集合
     * @return 是否更新成功(以更新成功数不为0判断是否成功)
     */
    boolean update(List<E> es);
    /**
     * 按条件更新集合
     * @param es 指定表记录集合
     * @param pattern 条件正则表达式
     * @return 更新是否成功（以更新成功数不为0判断 是否成功）
     */
    boolean update(List<E> es,String pattern);
    /**
     * 删除指定记录
     * @param e 指定表记录
     * @return 是否删除成功
     */
    boolean delete(E e);
    /**
     * 清空所有记录
     * @return 是否清空成功
     */
    boolean clear();
    /**
     * 按条件删除表记录
     * @param cnd 指定条件
     * @return 是否删除成功（删除成功数不为0为判断依据）
     */
    boolean clear(Condition cnd);
    /**
     * 按名称删除，需要指定@Name注解
     * @param name 指定删除name
     * @return 是否删除成功
     */
    boolean delete(String name);
    /**
     * 删除指定ID的记录（如果表结构有声明@ID）
     * @param id 指定ID
     * @return 是否删除成功
     */
    boolean delete(int id);
    /**
     * 查询所有
     * @return 查询结果集合
     */
    List<E> queryAll();
    /**
     * 按条件查找
     * @param cnd 指定条件
     * @return 查询查询集合
     */
    List<E> query(Condition cnd);
    /**
     * 分布查找
     * @param cnd 条件
     * @param pIndex 第几页
     * @param pageCount 每页多少条记录
     * @return 查询结果集合
     */
    List<E> query(Condition cnd,int pIndex, int pageCount);
}

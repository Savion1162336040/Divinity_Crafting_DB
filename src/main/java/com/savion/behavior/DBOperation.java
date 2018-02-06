package com.savion.behavior;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 */
public interface DBOperation<E> {
    //查询所有
    List<E> queryAll();
    //删除表
    boolean dropTable();
    //插入表
    boolean insert(E e);
    //更新表
    boolean update(E e);
    //按条件更新(parrtern为正则表达式-指定更新的字段,如:^name$仅更新name字段)
    boolean update(E e,String pattern);
    //更新集合
    boolean update(List<E> es);
    //按条件更新集合
    boolean update(List<E> es,String pattern);
    //删除指定记录
    boolean delete(E e);
    //按名称删除记录
    boolean delete(String name);
    //按条件查找
    E query();
}

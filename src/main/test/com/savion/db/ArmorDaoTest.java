package com.savion.db;

import com.savion.bean.Armor;
import org.junit.Test;
import org.nutz.dao.Cnd;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by savion on 2018/2/7.
 */
public class ArmorDaoTest {
    final ArmorDao dao = new ArmorDao();

    @Test
    public void dropTable() throws Exception {

    }

    @Test
    public void fetch() throws Exception {
        Armor armor = dao.fetch("savion");
        System.out.println(armor == null ? "no armor found" : armor.toString());
    }

    @Test
    public void insert() throws Exception {
        Armor armor = new Armor();
        armor.setName("Eternal Hallows");
        armor.setDescription("Anyone will be eternal with the Eternal Hallows!");
        boolean b = dao.insert(armor);
        System.out.println(b ? "insert success" : "insert failed");
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void update1() throws Exception {

    }

    @Test
    public void update2() throws Exception {

    }

    @Test
    public void update3() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void clear1() throws Exception {

    }

    @Test
    public void delete1() throws Exception {

    }

    @Test
    public void delete2() throws Exception {

    }

    @Test
    public void queryAll() throws Exception {
        List<Armor> armors = dao.queryAll();
        if (armors == null || armors.size() == 0) {
            System.out.println("query done,no rows found");
        } else
            armors.stream().forEach(System.out::println);
    }

    @Test
    public void query() throws Exception {
        List<Armor> armors = dao.query(Cnd.where("name", "=", "savion"));
        if (armors == null || armors.size() == 0) {
            System.out.println("query done,no rows found");
        } else
            armors.stream().forEach(System.out::println);
    }

    @Test
    public void query1() throws Exception {
        List<Armor> armors=dao.query(Cnd.where("name","like","Eternal%"),1,5);
        if (armors == null || armors.size() == 0) {
            System.out.println("query done,no rows found");
        } else
            armors.stream().forEach(System.out::println);
    }

}
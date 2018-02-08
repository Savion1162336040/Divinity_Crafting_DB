package com.savion.bean;

import com.savion.behavior.Synthetic;
import com.savion.db.ArmorDao;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savion on 2018/2/6.
 * 盔甲
 */
@Table(value = "divinity.armor")
public class Armor extends Items implements Synthetic {
    List<Synthetic> items;
    Json json;
    @Column(value = "item")
    String jsonStr;

    public Armor(){}
    public Armor(String name,String des){
        this.name = name;
        this.description = des;
    }

    public Json getJson() {
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }

    public List<Synthetic> getItems() {
        return items;
    }

    public void setItems(List<Synthetic> items) {
        this.items = items;
        String json = Json.toJson(items);
        jsonStr = json;
        System.out.println(json);
    }

    @Override
    public String toString() {
        String itemstring = "[";
        for (int i = 0; items != null && i < items.size(); i++) {
            itemstring += "item" + i + ":(" + items.get(i).toString() + "),";
        }
        itemstring += "]";
        return "Armor{" + itemstring
                + super.toString() +
                "} ";
    }

    public static void main(String[] strings){
        Armor armor = new Armor("present","present des");
        List<Synthetic> armors = new ArrayList<>();
        armors.add(new Armor("a","a d"));
        armors.add(new Armor("b","b d"));
        armor.setItems(armors);

        ArmorDao dao = new ArmorDao();
        boolean b = dao.insert(armor);
        System.out.println(b);
    }

    @Override
    public Synthetic Synthesis(Object o) {
        return null;
    }
}

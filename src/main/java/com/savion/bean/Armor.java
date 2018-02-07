package com.savion.bean;

import com.savion.behavior.Synthetic;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 * 盔甲
 */
@Table(value = "divinity.armor")
public class Armor extends Items implements Synthetic {
    @Column(value = "item")
    List<Synthetic> items;

    public Synthetic Synthesis(Object o) {
        return null;
    }

    public List<Synthetic> getItems() {
        return items;
    }

    public void setItems(List<Synthetic> items) {
        this.items = items;
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
}

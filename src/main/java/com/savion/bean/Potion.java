package com.savion.bean;

import com.savion.behavior.Synthetic;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * Created by savion on 2018/2/6.
 * 药水
 */
@Table(value = "divinity.potion")
public class Potion extends Items implements Synthetic {
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
}

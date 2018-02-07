package com.savion.bean;

import com.savion.main.Divinity2Parse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by savion on 2018/2/7.
 */
public class DivinityItem implements Serializable {

    private String name;
    private String description;
    private List<DivinityItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DivinityItem> getItems() {
        return items;
    }

    public void setItems(List<DivinityItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "DivinityItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
}

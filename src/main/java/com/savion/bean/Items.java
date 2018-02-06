package com.savion.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

/**
 * Created by savion on 2018/2/6.
 */
public abstract class Items {
    @Column(value = "name")
    @Name
    public String name;
    @Column(value = "description")
    public String description;

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
}

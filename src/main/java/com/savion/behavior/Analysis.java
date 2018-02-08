package com.savion.behavior;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by savion on 2018/2/8.
 */
public interface Analysis<T> {
    /**
     * 解析成实体列表
     */
    List<T> analysis(Document document) throws Exception;
}

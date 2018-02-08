package com.savion.main;

import com.savion.bean.Armor;
import com.savion.bean.DivinityItem;
import com.savion.behavior.Analysis;
import com.savion.behavior.Synthetic;
import com.savion.exception.DataEmptyException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savion on 2018/2/8.
 */
public class ArmorAnalysis implements Analysis<Armor> {
    @Override
    public List analysis(Document document) throws Exception {
        Elements table = document.getElementsByClass("divinitytable");
        if (table.size() == 0) {
            throw new DataEmptyException("analysis armor data empty!");
        }
        Element tablenode = table.get(0);
        Element body = tablenode.getElementsByTag("tbody").get(0);
        Elements trs = body.getElementsByTag("tr");
        List<Armor> items = new ArrayList<>();
        Armor item;
        for (int i = 1; trs != null && i < trs.size(); i++) {
            item = new Armor();
            Elements tds = trs.get(i).getElementsByTag("td");
            if (tds.size() < 3) {
                continue;
            }
            //物品名称
            if (tds.get(0).childNodeSize() == 1) {
                item.setName(tds.get(0).text());
            } else {
                item.setName(tds.get(0).getElementsByTag("a").text());
            }
            //多个组合材料
            Elements ps = tds.get(1).children();
            List<Synthetic> mixItems = new ArrayList<>();
            DivinityItem mixItem;
            for (int j = 0; ps != null && j < ps.size(); j++) {
                mixItem = new DivinityItem();
                String psas = ps.get(j).text();
                mixItem.setName(psas);
                mixItems.add(mixItem);
            }
            item.setItems(mixItems);
            //描述
            item.setDescription(tds.get(2).text());
            items.add(item);
        }
        return items;
    }
}

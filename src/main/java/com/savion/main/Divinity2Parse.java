package com.savion.main;

import com.savion.bean.DivinityItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by savion on 2018/2/7.
 */
public class Divinity2Parse implements JSoupImp<DivinityItem> {
    //解析地址
    public static String DIVINITY_URL = "http://www.irodemine.com/divinity2/divinity2.php";
    //参数搜索类别
    public static String PARAM_SEARCH_CATEGORY = "selection";
    //参数搜索关键字
    public static String PARAM_SEARCH_TEXT = "searchMe";

    private String cURL = DIVINITY_URL;

    private OnParseCallback<DivinityItem> mcallback = null;
    /**
     * 搜索分类
     * 0:全部
     * 1:weapon
     * 2:potion
     * 3:Grenades
     * 4:Arrows & Arrow Heads
     * 5:Armor & Upgrades
     * 6:Food & Drink
     * 7:Misc
     * 8:Runes & components
     * 9:Scrolls & Books
     */
    private String searchSection = "0";
    /**
     * 搜索关键字
     * 空则为无关键字搜索,搜索全部
     */
    private String searchMe = "";

    private void get(String category, String text) {
        System.out.println("start to get:url = ["+cURL+"] , searchSection = ["+searchSection+"] , searchMe = ["+searchMe+"]");
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document document = Jsoup.connect(cURL).data(PARAM_SEARCH_CATEGORY, category).data(PARAM_SEARCH_TEXT, text).timeout(10000).post();
                    Elements table = document.getElementsByClass("divinitytable");
                    if (table.size() == 0) {
                        if (mcallback != null)
                            mcallback.empty();
                        return;
                    }
                    Element tablenode = table.get(0);
                    Element body = tablenode.getElementsByTag("tbody").get(0);
                    Elements trs = body.getElementsByTag("tr");
                    List<DivinityItem> items = new ArrayList<DivinityItem>();
                    DivinityItem item;
                    for (int i = 1; trs != null && i < trs.size(); i++) {
                        item = new DivinityItem();
                        Elements tds = trs.get(i).getElementsByTag("td");
                        if (tds.size()<3){
                            continue;
                        }
                        //物品名称
                        if (tds.get(0).childNodeSize()==1){
                            item.setName(tds.get(0).text());
                        }else{
                            item.setName(tds.get(0).getElementsByTag("a").text());
                        }
                        //多个组合材料
                        Elements ps = tds.get(1).children();
                        List<DivinityItem> mixItems = new ArrayList<>();
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
                    if (items != null) {
                        if (mcallback != null) {
                            mcallback.success(items);
                        }
                    } else {
                        if (mcallback != null) {
                            mcallback.empty();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (mcallback != null) {
                        mcallback.error();
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * 执行方法
     */
    @Override
    public void parse() {
        //获取全部数据
        get(this.searchSection, this.searchMe);
    }

    /**
     * 获取地址设置
     * @param url
     * @return
     */
    @Override
    public JSoupImp url(String url) {
        this.cURL = url != null && url.length() > 0 ? url : DIVINITY_URL;
        parse();
        return this;
    }

    /**
     * 参数设置
     * @param selection 参数列表,接收参数如下:
     * @see #searchSection
     * @see #searchMe
     * @return
     */
    @Override
    public JSoupImp params(Map<String,String> selection) {
        this.searchSection = selection.getOrDefault("searchSection","0");
        this.searchMe = selection.getOrDefault("searchMe","");
        return this;
    }

    /**
     * 回调设置
     * @param callback
     * @return
     */
    @Override
    public JSoupImp callback(OnParseCallback callback) {
        this.mcallback = callback;
        return this;
    }

    public static void main(String[] strings){
        JSoupImp parse = new Divinity2Parse();
        parse.callback(new OnParseCallback<DivinityItem>() {
            @Override
            public void success(List<DivinityItem> divinityItems) {
                divinityItems.forEach(System.out::println);
            }

            @Override
            public void error() {
                System.out.println("divinity parse error");
            }

            @Override
            public void empty() {
                System.out.println("divinity parse empty");
            }
        });
        Map map = new HashMap();
        map.put("searchSection","1");
        parse.params(map);
        parse.parse();
    }
}

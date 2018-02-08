package com.savion.main;

import com.savion.bean.Armor;
import com.savion.bean.Items;
import com.savion.behavior.Analysis;
import com.savion.behavior.Synthetic;
import com.savion.db.ArmorDao;
import com.savion.exception.DataEmptyException;
import com.savion.exception.DataParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

/**
 * Created by savion on 2018/2/7.
 */
public class Divinity2Parse<T extends Items, E extends Synthetic> implements JSoupImp<E> {
    //解析地址
    public static String DIVINITY_URL = "http://www.irodemine.com/divinity2/divinity2.php";
    //参数搜索类别
    public static final String PARAM_SEARCH_CATEGORY = "selection";
    //参数搜索关键字
    public static final String PARAM_SEARCH_TEXT = "searchMe";

    private Map<String, String> params = null;//new HashMap<>();

    private String cURL = DIVINITY_URL;

    private OnParseCallback<T> mcallback = null;
    private Analysis<T> mAnalysis;
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

    private void addParams() {
        if (this.params != null) {
            Map<String, String> tempMap = params;
            int index = this.cURL.indexOf("?");
            String newUrl;
            if (index > 0) {
                String par = this.cURL.substring(index + 1);
                String[] pars = par.split("&");
                newUrl = cURL.substring(0, index + 1);
                for (int i = 0; i < pars.length; i++) {
                    String[] en = pars[i].split("=");
                    if (en != null && en.length > 0) {
                        String[] news = new String[2];
                        if (tempMap.containsKey(en[0])) {
                            news[0] = en[0];
                            news[1] = tempMap.get(en[0]);
                            newUrl += en[0] + "=" + tempMap.get(en[0]) + "&";
                            tempMap.remove(en[0]);
                        }
                    } else {
                        newUrl += pars[i] + "&";
                    }
                }
                for (String key : tempMap.keySet()) {
                    newUrl += key + "=" + tempMap.get(key) + "&";
                }
            } else {
                newUrl = cURL + "?";
                for (String key : tempMap.keySet()) {
                    newUrl += key + "=" + tempMap.get(key) + "&";
                }
            }
            cURL = newUrl;
        }
        System.out.println(cURL);
    }

    private void get() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    addParams();
                    System.out.println("start to get:url = [" + cURL + "] , searchSection = [" + searchSection + "] , searchMe = [" + searchMe + "]");
                    Document document = Jsoup.connect(cURL).timeout(10000).post();
                    if (document == null) {
                        if (mcallback != null) {
                            mcallback.error("Unreceived target's data");
                        }
                        return;
                    }
                    List result = mAnalysis.analysis(document);
                    if (result != null) {
                        if (mcallback != null) {
                            mcallback.success(result);
                        }
                    } else {
                        if (mcallback != null) {
                            mcallback.empty();
                        }
                    }
                } catch (IOException e) {
                    if (mcallback != null) {
                        mcallback.error("IOException:"+e.getMessage());
                    }
                } catch (DataEmptyException e) {
                    if (mcallback != null) {
                        mcallback.empty();
                    }
                } catch (DataParseException e) {
                    if (mcallback != null) {
                        mcallback.error("DataParseException:"+e.getMessage());
                    }
                } catch (Exception e) {
                    if (mcallback != null)
                        mcallback.error("Exception:"+e.getMessage());
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
        get();
    }

    /**
     * 获取地址设置
     *
     * @param url
     * @return
     */
    @Override
    public JSoupImp url(String url) {
        this.cURL = url != null && url.length() > 0 ? url : DIVINITY_URL;
        return this;
    }

    /**
     * 参数设置
     *
     * @param selection 参数列表,接收参数如下:
     * @return
     * @see #searchSection
     * @see #searchMe
     */
    @Override
    public JSoupImp params(Map<String, String> selection) {
        this.searchSection = selection.getOrDefault(PARAM_SEARCH_CATEGORY, "0");
        this.searchMe = selection.getOrDefault(PARAM_SEARCH_TEXT, "");
        this.params = new HashMap<String, String>();
        this.params.put(PARAM_SEARCH_CATEGORY, this.searchSection);
        this.params.put(PARAM_SEARCH_TEXT, this.searchMe);
        return this;
    }

    @Override
    public JSoupImp<E> callDocumentAnalysis(Analysis analysis) {
        this.mAnalysis = analysis;
        return this;
    }

    /**
     * 回调设置
     *
     * @param callback
     * @return
     */
    @Override
    public JSoupImp callback(OnParseCallback callback) {
        this.mcallback = callback;
        return this;
    }

    public static void main(String[] strings) {

        JSoupImp parse = new Divinity2Parse();
        parse.callback(new OnParseCallback<Armor>() {
            @Override
            public void success(List<Armor> divinityItems) {
                divinityItems.forEach(System.out::println);
                ArmorDao dao = new ArmorDao();
            }
            @Override
            public void error(String msg) {
                System.out.println(String.format("divinity parse error:%s",msg));
            }
            @Override
            public void empty() {
                System.out.println("divinity parse empty");
            }
        });
        Map map = new HashMap();
        map.put("selection", "5");
        parse.params(map);
        //解析器
        parse.callDocumentAnalysis(new ArmorAnalysis());
        parse.parse();
    }
}

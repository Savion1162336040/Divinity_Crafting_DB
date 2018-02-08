package com.savion.main;

import com.savion.behavior.Analysis;

import java.util.Map;

/**
 * Created by savion on 2018/2/7.
 */
public interface JSoupImp<E> {
    /**
     * 开始获取
     */
    void parse();
    /**
     * 获取地址设置
     * @param url
     */
    JSoupImp<E> url(String url);
    /**
     * 参数设置
     * @param map 参数表
     * @return
     */
    JSoupImp<E> params(Map<String,String> map);
    /**
     * 回调设置
     * @param callback
     * @return
     */
    JSoupImp<E> callback(OnParseCallback<E> callback);

    /**
     * 传递实体解析器
     * @return
     */
    JSoupImp<E> callDocumentAnalysis(Analysis analysis);
}

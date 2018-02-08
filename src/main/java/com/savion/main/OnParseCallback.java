package com.savion.main;

import java.util.List;

/**
 * Created by savion on 2018/2/7.
 */
public interface OnParseCallback<E> {
    void success(List<E> es);
    void error(String msg);
    void empty();
}

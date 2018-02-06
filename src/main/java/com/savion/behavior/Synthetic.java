package com.savion.behavior;

/**
 * Created by savion on 2018/2/6.
 * 可合成的项
 */
public interface Synthetic<E> {
    Synthetic Synthesis(E e);
}

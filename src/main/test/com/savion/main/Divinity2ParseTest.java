package com.savion.main;

import com.savion.bean.DivinityItem;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by savion on 2018/2/7.
 */
public class Divinity2ParseTest {
    @Test
    public void parse() throws Exception {
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
        parse.parse();
    }

    @Test
    public void parse1() throws Exception {

    }

    @Test
    public void callback() throws Exception {

    }

}
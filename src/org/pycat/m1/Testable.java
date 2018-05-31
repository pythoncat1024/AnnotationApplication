package org.pycat.m1;

import org.pycat.m1.Test;

/**
 * Created by cat on 2018/5/31.
 * 被注解的类
 */
public class Testable {

    public void execute() {
        System.out.println("Executing");
    }

    @Test
    void testExecute() {
        execute();
    }
}

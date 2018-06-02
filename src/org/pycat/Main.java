package org.pycat;

import org.pycat.m4.CreateTable;
import org.pycat.m4.TableCreator;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	// write your code here

//        TableCreator.execute(new String[]{"org.pycat.m4.Member"});

        CreateTable.execute("org.pycat.m4.Member");
    }
}

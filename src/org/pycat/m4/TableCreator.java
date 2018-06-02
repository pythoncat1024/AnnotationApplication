package org.pycat.m4;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cat on 2018/6/2.
 * 注解处理器
 */
public class TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        execute(new String[]{"org.pycat.m4.Member"});

    }

    public static void execute(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("arguments:annotated classes");
            System.exit(0);
        }

        for (String className : args) {
            String tableCreate = null;
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            // if this name is empty , use class name
            if (tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<String>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1)
                    continue; // not a db table column
                if (anns[0] instanceof SQLInteger) {
//                    System.err.println("xxxxxxxx");
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // use field name if name not specified
                    if (sInt.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constrains()));

                }
                if (anns[0] instanceof SQLString) {
//                    System.err.println("zzzzzzzzzz");
                    SQLString sString = (SQLString) anns[0];
                    // use field name if name not specified.
                    if (sString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constrains()));
                }
                StringBuilder createdCommand = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "(");
                for (String columnDef : columnDefs) {
                    createdCommand.append("\n   " + columnDef + ",");
                }
                // remove trailing name
                tableCreate = createdCommand.substring(0, createdCommand.length() - 1) + ");";
            }
            System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreate);
        }
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }
}





















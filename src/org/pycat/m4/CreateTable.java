package org.pycat.m4;

import java.lang.reflect.Field;

/**
 * Created by cat on 2018/6/2.
 * 注解处理器 ==》 对注解的信息进行处理，不然注解就没有任何的意义
 */
public class CreateTable {

    public static void execute(String className) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(className);
        String sql = null;
        StringBuilder sqlCommand = new StringBuilder();
        DBTable db = aClass.getDeclaredAnnotation(DBTable.class);
        if (db == null) {
            System.err.println("is not a db table");
            System.exit(0);
        }

        String tableName = db.name();
        if (tableName.length() < 1) {
            tableName = aClass.getName().toUpperCase(); // 如果 name 为 ""，则为类名大写
        }
        sqlCommand.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append("(");
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            SQLInteger sInt = field.getAnnotation(SQLInteger.class);
            if (sInt != null) {
                // 说明当前字段有 SQLInteger 的注解
                String column = sInt.name(); // 字段名
                if (column.length() < 1) {
                    column = field.getName().toUpperCase();
                }
                sqlCommand.append("\n   ").append(column).append(" INT");
                Constraints constrains = sInt.constrains();
                sqlCommand.append(handleConstraints(constrains)).append(",");
            }
            SQLString sString = field.getAnnotation(SQLString.class);
            if (sString != null) {
                String column = sString.name();
                if (column.length() < 1) {
                    column = field.getName().toUpperCase();
                }
                int len = sString.value();
                Constraints con = sString.constrains();
                sqlCommand.append("\n   ")
                        .append(column).append(" VARCHAR(").append(len).append(")")
                        .append(handleConstraints(con))
                        .append(",");
            }


        }
        sql = sqlCommand.toString().substring(0, sqlCommand.length() - 1) + ");";

        System.out.println("sql:\n" + sql);
    }

    private static String handleConstraints(Constraints con) {
        String constraints = "";
        boolean unique = con.unique();
        if (unique) {
            constraints += " UNIQUE";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        return constraints;
    }
}
/**
 * <pre>
 * CREATE TABLE IF NOT EXISTS Member(
 * FIRSTNAME VARCHAR(30),
 * LASTNAME VARCHAR(50),
 * AGE INT,
 * HANDLE VARCHAR(30) PRIMARY KEY);
 * </pre>
 */
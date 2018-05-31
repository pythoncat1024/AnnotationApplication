package org.pycat.m4;

import java.time.temporal.ValueRange;

/**
 * Created by cat on 2018/5/31.
 * JavaBean 对象
 */
@DBTable(name = "Member")
public class Member {
    @SQLString(30)
    String firstName;
    @SQLString(value = 50)
    String lastName; // 和 @SQLString(50) 一样的
    @SQLInteger
    Integer age;

    @SQLString(
            value = 30,
            constrains = @Constraints(primaryKey = true)
    ) // 表示 handle value=30 , primaryKey=true , unique = default , allowNull = default
            // todo: yes ???
            String handle;
    static int memberCount;

    public String getHandle() {
        return handle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return handle;
    }

    public Integer getAge() {
        return age;
    }
}

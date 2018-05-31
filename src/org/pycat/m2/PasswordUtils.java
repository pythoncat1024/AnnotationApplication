package org.pycat.m2;

import java.util.List;

/**
 * Created by cat on 2018/5/31.
 * 使用注解的类
 */
public class PasswordUtils {
    @UserCase(id = 47, description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    @UserCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuffer(password).reverse().toString();
    }

    @UserCase(id = 49, description = "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prePasswords, String password) {
        return !prePasswords.contains(password);
    }
}






















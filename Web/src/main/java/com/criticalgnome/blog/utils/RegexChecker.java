package com.criticalgnome.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
public class RegexChecker {
    public static boolean checkNickNameWithRegExp(String s){
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static boolean checkNameWithRegExp(String s){
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}

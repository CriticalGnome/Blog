package com.criticalgnome.blog.actions.locale;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
class LocaleFactory {
    static String getLocale(String key) {
        String locale;
        LocalesTable entry = LocalesTable.valueOf(key.toUpperCase());
        locale = entry.getLocale();
        return locale;
    }
}

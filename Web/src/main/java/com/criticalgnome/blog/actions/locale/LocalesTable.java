package com.criticalgnome.blog.actions.locale;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public enum LocalesTable {
    DEFAULT ("locale_en_US"),
    RU ("locale_ru_RU");

    String locale;

    public String getLocale() {
        return locale;
    }

    LocalesTable(String locale) {
        this.locale = locale;
    }
}

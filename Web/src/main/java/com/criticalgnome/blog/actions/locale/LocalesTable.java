package com.criticalgnome.blog.actions.locale;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public enum LocalesTable {
    DEFAULT {{ locale="locale_en_US"; }},
    RU {{ locale="locale_ru_RU"; }};
    String locale;
    public String getLocale() {
        return locale;
    }
}

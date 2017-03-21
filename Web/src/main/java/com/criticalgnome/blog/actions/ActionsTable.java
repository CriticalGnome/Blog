package com.criticalgnome.blog.actions;

import com.criticalgnome.blog.actions.locale.ActionChangeLocale;
import com.criticalgnome.blog.actions.user.ActionLogin;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public enum ActionsTable {
    LOGIN {{ this.action = new ActionLogin(); }},
    CHANGELOCALE {{ this.action = new ActionChangeLocale(); }};

    Action action;

    public Action getCommand() {
        return action;
    }
}

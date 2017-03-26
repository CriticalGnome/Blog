package com.criticalgnome.blog.actions;

import com.criticalgnome.blog.actions.record.ActionDeleteRecord;
import com.criticalgnome.blog.actions.record.ActionEditRecord;
import com.criticalgnome.blog.actions.content.ActionMainpage;
import com.criticalgnome.blog.actions.record.ActionSaveRecord;
import com.criticalgnome.blog.actions.record.ActionWriteRecord;
import com.criticalgnome.blog.actions.locale.ActionChangeLocale;
import com.criticalgnome.blog.actions.user.ActionLogin;
import com.criticalgnome.blog.actions.user.ActionLogout;
import com.criticalgnome.blog.actions.user.ActionRegister;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public enum ActionsTable {
    MAINPAGE {{ this.action = new ActionMainpage(); }},
    LOGIN {{ this.action = new ActionLogin(); }},
    LOGOUT {{ this.action = new ActionLogout(); }},
    REGISTER {{ this.action = new ActionRegister(); }},
    WRITERECORD {{ this.action = new ActionWriteRecord(); }},
    EDITRECORD {{ this.action = new ActionEditRecord(); }},
    SAVERECORD {{ this.action = new ActionSaveRecord(); }},
    DELETERECORD {{ this.action = new ActionDeleteRecord(); }},
    CHANGELOCALE {{ this.action = new ActionChangeLocale(); }};

    Action action;

    public Action getCommand() {
        return action;
    }
}

package com.criticalgnome.blog.actions;

import com.criticalgnome.blog.actions.admin.ActionAddCategory;
import com.criticalgnome.blog.actions.admin.ActionAdminArea;
import com.criticalgnome.blog.actions.admin.ActionDeleteCategory;
import com.criticalgnome.blog.actions.admin.ActionEditCategory;
import com.criticalgnome.blog.actions.content.ActionChangePagination;
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
    MAINPAGE (new ActionMainpage()),
    CHANGEPAGINATION (new ActionChangePagination()),
    LOGIN (new ActionLogin()),
    LOGOUT (new ActionLogout()),
    REGISTER (new ActionRegister()),
    WRITERECORD (new ActionWriteRecord()),
    EDITRECORD (new ActionEditRecord()),
    SAVERECORD (new ActionSaveRecord()),
    DELETERECORD (new ActionDeleteRecord()),
    ADDCATEGORY (new ActionAddCategory()),
    EDITCATEGORY (new ActionEditCategory()),
    DELETECATEGORY (new ActionDeleteCategory()),
    ADMINAREA (new ActionAdminArea()),
    CHANGELOCALE (new ActionChangeLocale());

    Action action;

    public Action getCommand() {
        return action;
    }

    ActionsTable(Action action) {
        this.action = action;
    }
}

package com.criticalgnome.blog.constants;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class SqlTables {

    // Category table
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "name";
    public static final String CATEGORY_CATEGORY_ID = "category_id";
    // Record table
    public static final String RECORD_ID = "id";
    public static final String RECORD_HEADER = "header";
    public static final String RECORD_BODY = "body";
    public static final String RECORD_DATE = "date";
    public static final String RECORD_CATEGORY_ID = "category_id";
    public static final String RECORD_USER_ID = "user_id";
    // Role Table
    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "name";
    // Tag Table
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
}

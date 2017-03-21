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
    // Role table
    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "name";
    // Tag table
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    // User table
    public static final String USER_ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NICK_NAME = "nick_name";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_ROLE_ID = "role_id";
}

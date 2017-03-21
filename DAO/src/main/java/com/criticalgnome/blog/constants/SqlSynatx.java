package com.criticalgnome.blog.constants;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class SqlSynatx {

    // Category table
    public static final String INSERT_INTO_CATEGORY_VALUES = "INSERT INTO category (id, name, category_id) VALUES (?, ?, ?);";
    public static final String SELECT_FROM_CATEGORY = "SELECT * FROM category;";
    public static final String SELECT_FROM_CATEGORY_WHERE = "SELECT * FROM category WHERE id = ?;";
    public static final String UPDATE_CATEGORY_SET_WHERE = "UPDATE category SET name = ?, category_id = ? WHERE id = ?";
    public static final String DELETE_FROM_CATEGORY_WHERE = "DELETE FROM category WHERE id = ?;";
    public static final String SELECT_MAX_FROM_CATEGORY = "SELECT max(id) FROM category;";
    // Record table
    public static final String INSERT_INTO_RECORD_VALUES = "INSERT INTO record (id, header, body, category_id, user_id) VALUES (?, ?, ?, ?, ?);";
    public static final String SELECT_FROM_RECORD = "SELECT * FROM record;";
    public static final String SELECT_FROM_RECORD_WHERE = "SELECT * FROM record WHERE id = ?;";
    public static final String UPDATE_RECORD_SET_WHERE = "UPDATE record SET header = ?, body = ?, category_id = ?, user_id = ? WHERE id = ?;";
    public static final String DELETE_FROM_RECORD_WHERE = "DELETE FROM record WHERE id = ?;";
    public static final String SELECT_MAX_FROM_RECORD = "SELECT max(id) FROM record;";
    // Role table
    public static final String INSERT_INTO_ROLE_VALUES = "INSERT INTO role (id, name) VALUES (?, ?);";
    public static final String SELECT_FROM_ROLE = "SELECT * FROM role;";
    public static final String SELECT_FROM_ROLE_WHERE = "SELECT * FROM role WHERE id = ?;";
    public static final String UPDATE_ROLE_SET_WHERE = "UPDATE role SET name = ? WHERE id = ?;";
    public static final String DELETE_FROM_ROLE_WHERE = "DELETE FROM role WHERE id = ?;";
    public static final String SELECT_MAX_FROM_ROLE = "SELECT max(id) FROM role;";
    // Tag table
    public static final String INSERT_INTO_TAG_VALUES = "INSERT INTO tag (id, name) VALUES (?, ?);";
    public static final String SELECT_FROM_TAG = "SELECT * FROM tag;";
    public static final String SELECT_FROM_TAG_WHERE = "SELECT * FROM tag WHERE id = ?;";
    public static final String UPDATE_TAG_SET_WHERE = "UPDATE tag SET name = ? WHERE id = ?;";
    public static final String DELETE_FROM_TAG_WHERE = "DELETE FROM tag WHERE id = ?;";
    public static final String SELECT_MAX_FROM_TAG = "SELECT max(id) FROM tag;";
    public static final String SELECT_FROM_TAG_WHERE_RECORD_HAS_TAG_RECORD_ID = "SELECT * FROM tag WHERE record_has_tag.record_id = ?;";
    // User table
    public static final String INSERT_INTO_USER_VALUES = "INSERT INTO user (id, email, password, role_id, nick_name, first_name, last_name) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String SELECT_FROM_USER = "SELECT * FROM user;";
    public static final String SELECT_FROM_USER_WHERE = "SELECT * FROM user WHERE id = ?;";
    public static final String UPDATE_USER_SET_WHERE = "UPDATE user SET email = ?, password = ?, nick_name = ?, first_name = ?, last_name = ?, role_id = ? WHERE id = ?;";
    public static final String DELETE_FROM_USER_WHERE = "DELETE FROM user WHERE id = ?;";
    public static final String SELECT_MAX_FROM_USER = "SELECT max(id) FROM user;";
}

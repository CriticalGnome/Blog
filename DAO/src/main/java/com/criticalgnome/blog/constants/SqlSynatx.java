package com.criticalgnome.blog.constants;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class SqlSynatx {

    // Category table
    public static final String INSERT_INTO_VALUES = "INSERT INTO category (id, name, category_id) VALUES (?, ?, ?);";
    public static final String SELECT_FROM = "SELECT * FROM category;";
    public static final String SELECT_FROM_WHERE = "SELECT * FROM category WHERE id = ?;";
    public static final String UPDATE_SET_WHERE = "UPDATE category SET name = ?, category_id = ? WHERE id = ?";
    public static final String DELETE_FROM_WHERE = "DELETE FROM category WHERE id = ?;";
    public static final String SELECT_MAXID_FROM = "SELECT max(id) FROM category;";

}

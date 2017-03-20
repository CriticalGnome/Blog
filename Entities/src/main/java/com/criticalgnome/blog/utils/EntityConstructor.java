package com.criticalgnome.blog.utils;

import com.criticalgnome.blog.entities.*;

import java.sql.Timestamp;

/**
 * Constructors for all entities
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class EntityConstructor {

    /**
     * Builder for <b>Category</b> object
     * @param id id
     * @param name category name
     * @param parentCategory parent category
     * @return created object
     */
    public static Category buildCategory(int id, String name, Category parentCategory) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setCategory(parentCategory);
        return category;
    }

    /**
     * Builder for <b>Record</b> object
     * @param id id
     * @param header record header
     * @param body record body
     * @param timestamp record date and time
     * @param category record category
     * @param author record author
     * @return created object
     */
    public static Record buildRecord(int id, String header, String body, Timestamp timestamp, Category category, User author) {
        Record record = new Record();
        record.setId(id);
        record.setHeader(header);
        record.setBody(body);
        record.setTimestamp(timestamp);
        record.setCategory(category);
        record.setAuthor(author);
        return record;
    }

    /**
     * Builder for <b>Role</b> object
     * @param id id
     * @param name role name
     * @return created object
     */
    public static Role buildRole(int id, String name) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        return role;
    }

    /**
     * Builder for <b>Tag</b> object
     * @param id id
     * @param name tag name
     * @return created object
     */
    public static Tag buildTag(int id, String name) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        return tag;
    }

    /**
     * Builder for <b>User</b> object
     * @param id id
     * @param email user email
     * @param password user password
     * @param nickName nickname
     * @param firstName first name
     * @param lastName last name
     * @param role user role
     * @return created object
     */
    public static User buildUser(int id, String email, String password, String nickName, String firstName, String lastName, Role role) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setNickName(nickName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        return user;
    }
}

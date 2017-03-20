package com.criticalgnome.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class Record extends Entity implements Serializable {
    private String header;
    private String body;
    private Timestamp timestamp;
    private Category category;
    private User author;
    private List<Tag> tags;

    @Override
    public String toString() {
        return "Record{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", timestamp=" + timestamp +
                ", category=" + category +
                ", author=" + author +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Record record = (Record) o;

        if (header != null ? !header.equals(record.header) : record.header != null) return false;
        if (body != null ? !body.equals(record.body) : record.body != null) return false;
        if (timestamp != null ? !timestamp.equals(record.timestamp) : record.timestamp != null) return false;
        if (category != null ? !category.equals(record.category) : record.category != null) return false;
        if (author != null ? !author.equals(record.author) : record.author != null) return false;
        return tags != null ? tags.equals(record.tags) : record.tags == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}

package com.criticalgnome.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "records")
public class Record extends AbstractEntity implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id @GeneratedValue
    private Long id;
    @Column
    private String header;
    @Column
    private String body;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "modified_at", insertable = false)
    private Timestamp modifiedAt;
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "author_id")
    private User author;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "record_has_tag",
            joinColumns = {@JoinColumn(name = "record_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
            )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Record record = (Record) o;

        if (id != null ? !id.equals(record.id) : record.id != null) return false;
        if (header != null ? !header.equals(record.header) : record.header != null) return false;
        if (body != null ? !body.equals(record.body) : record.body != null) return false;
        if (createdAt != null ? !createdAt.equals(record.createdAt) : record.createdAt != null) return false;
        if (modifiedAt != null ? !modifiedAt.equals(record.modifiedAt) : record.modifiedAt != null) return false;
        if (category != null ? !category.equals(record.category) : record.category != null) return false;
        return author != null ? author.equals(record.author) : record.author == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (modifiedAt != null ? modifiedAt.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}

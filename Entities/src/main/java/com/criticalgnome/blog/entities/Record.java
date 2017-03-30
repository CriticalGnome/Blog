package com.criticalgnome.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "records")
public class Record extends Pojo implements Serializable {

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
    private Set<Tag> tags = new HashSet<Tag>();
}

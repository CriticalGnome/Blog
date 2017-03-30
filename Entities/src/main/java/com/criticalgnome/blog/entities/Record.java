package com.criticalgnome.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "author_id")
    private User author;
    @ManyToMany
    private List<Tag> tags;
}

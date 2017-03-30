package com.criticalgnome.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "record")
public class Record extends Pojo implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @Column
    private String header;
    @Column
    private String body;
    @Column
    private Timestamp timestamp;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "id")
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "id")
    private User author;
    @ManyToMany
    private List<Tag> tags;
}

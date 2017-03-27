package com.criticalgnome.blog.entities;

import lombok.*;

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
public class Record extends Entity implements Serializable {

    private Integer id;
    private String header;
    private String body;
    private Timestamp timestamp;
    private Category category;
    private User author;
    private List<Tag> tags;
}

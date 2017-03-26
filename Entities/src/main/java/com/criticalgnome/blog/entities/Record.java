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
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Record extends Entity implements Serializable {

    @NonNull private Integer id;
    @NonNull private String header;
    @NonNull private String body;
    @NonNull private Timestamp timestamp;
    @NonNull private Category category;
    @NonNull private User author;
    @NonNull private List<Tag> tags;
}

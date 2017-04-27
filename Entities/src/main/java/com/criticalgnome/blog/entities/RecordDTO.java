package com.criticalgnome.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Blog
 * Created on 28.04.2017.
 *
 * @author CriticalGnome
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {

    private Long id;
    private String header;
    private String body;
    private Long categoryId;
    private Long authorId;
    private String tags;
}

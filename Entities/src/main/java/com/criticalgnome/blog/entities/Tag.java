package com.criticalgnome.blog.entities;

import lombok.*;

import java.io.Serializable;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Tag extends Entity implements Serializable {

    private Integer id;
    private String name;
}

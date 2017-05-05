package com.criticalgnome.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "category_id")
    private Category category;

}

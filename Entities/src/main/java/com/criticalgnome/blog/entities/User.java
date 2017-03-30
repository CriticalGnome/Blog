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
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tag")
public class User extends Pojo implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "id")
    private Role role;
}

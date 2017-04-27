package com.criticalgnome.blog.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.*;
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
@Builder
@Entity
@Table(name = "users")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractEntity implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "{user.email.notblank}")
    private String email;

    @Column
    @NotBlank(message = "{user.password.notblank}")
    @Size(min = 6, max = 32, message = "{user.password.size}")
    private String password;

    @Column(name = "nick_name", unique = true)
    @NotBlank(message = "{user.nickname.notblank}")
    @Size(min = 3, max = 20, message = "{user.nickname.size}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9]+$", message = "{user.nickname.pattern}")
    private String nickName;

    @Column(name = "first_name")
    @NotBlank(message = "{user.firstname.notblank}")
    @Size(min = 2, max = 20, message = "{user.firstname.size}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "{user.firstname.pattern}")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "{user.lastname.notblank}")
    @Size(min = 3, max = 50, message = "{user.lastname.size}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я-/s]+$", message = "{user.lastname.pattern}")
    private String lastName;

    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "role_id")
    private Role role;
}

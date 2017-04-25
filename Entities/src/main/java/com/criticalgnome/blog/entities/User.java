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
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Column
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 32, message = "Password should be between 6 and 32 characters long")
    private String password;

    @Column(name = "nick_name", unique = true)
    @NotBlank(message = "Nickname cannot be empty")
    @Size(min = 3, max = 20, message = "Nickname should be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9]+$", message = "Nickname should be alphanumeric with no spaces")
    private String nickName;

    @Column(name = "first_name")
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "First name should be alphabetic with no spaces")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 3, max = 50, message = "Last name should be between 3 and 50 characters long")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я-/s]+$", message = "First name should be alphabetic with no spaces")
    private String lastName;

    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "role_id")
    private Role role;
}

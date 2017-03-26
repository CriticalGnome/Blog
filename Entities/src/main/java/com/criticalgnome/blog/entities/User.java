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
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends Entity implements Serializable {

    @NonNull private Integer id;
    @NonNull private String email;
    @NonNull private String password;
    @NonNull private String nickName;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private Role role;
}

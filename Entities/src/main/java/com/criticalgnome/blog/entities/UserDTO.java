package com.criticalgnome.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Project Blog
 * Created on 28.04.2017.
 *
 * @author CriticalGnome
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String fullName;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Collection<GrantedAuthority> authorities;
}

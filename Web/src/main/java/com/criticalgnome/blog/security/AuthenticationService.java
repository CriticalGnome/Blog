package com.criticalgnome.blog.security;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.entities.UserDTO;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRoleService;
import com.criticalgnome.blog.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Project Blog
 * Created on 28.04.2017.
 *
 * @author CriticalGnome
 */
@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {

    private final IUserService userService;
    private final IRoleService roleService;

    @Autowired
    public AuthenticationService(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getByEmail(email);
            if (user == null) throw new UsernameNotFoundException("User not found");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new UserDTO(user != null ? user.getId() : null,
                user != null ? user.getEmail() : null,
                user != null ? user.getPassword() : null,
                user != null ? user.getNickName() : null,
                user != null ? user.getFirstName() + " " + user.getLastName() : null,
                true,
                true,
                true,
                true,
                setAuthorities(user != null ? user.getRole().getId() : null));
    }

    private Collection<GrantedAuthority> setAuthorities(Long roleId){
        List<GrantedAuthority> authorities = new ArrayList<>();
        try {
            List<Role> roles = roleService.getAll();
            for (Role role : roles) {
                if (role.getId().equals(roleId)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return authorities;
    }
}

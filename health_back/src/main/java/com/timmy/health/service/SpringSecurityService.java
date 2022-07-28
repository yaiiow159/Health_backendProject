package com.timmy.health.service;

import com.timmy.health.domain.Role;
import com.timmy.health.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Slf4j
public class SpringSecurityService implements UserDetailsService {

    @DubboReference(interfaceClass = UserService.class)
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        Set<Role> roles = Objects.requireNonNull(user).getRoles();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Role role: roles) {
            //add user the roles
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getKeyword()));
            //add user the permissions
            role.getPermissions().forEach(p -> grantedAuthorities.add(new SimpleGrantedAuthority(p.getKeyword())));
        }

        return new org.springframework.security.core.userdetails.User(username
                ,user.getPassword()
                ,grantedAuthorities);
    }
}

package com.freimanvs.banners.bannersystem.dao;

import com.freimanvs.banners.bannersystem.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;

@Repository
public class UserDAO {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    public String add(String username, String password) {
        UserDetails userDetails = createUser(username, password, true);
        jdbcUserDetailsManager.createUser(userDetails);
        return userDetails.getUsername();
    }

    public void delete(String username) {
        jdbcUserDetailsManager.deleteUser(username);
    }

    public void update(String username, String password, boolean enabled) {
        jdbcUserDetailsManager.updateUser(updateUser(username, password, enabled));
    }

    public User getByUsername(String username) {
        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(username);
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(userDetails.isEnabled());
        return user;
    }

    private UserDetails updateUser(String username, String password, boolean enabled) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> col = new HashSet<>();
                col.add(() -> "ROLE_USER");
                return col;
            }

            @Override
            public String getPassword() {
                return String.format("{%s}%s", "bcrypt", new BCryptPasswordEncoder().encode(password));
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return enabled;
            }
        };
    }

    private UserDetails createUser(String username, String password, boolean enabled) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> col = new HashSet<>();
                col.add(() -> "ROLE_USER");
                return col;
            }

            @Override
            public String getPassword() {
                return String.format("{%s}%s", "bcrypt", new BCryptPasswordEncoder().encode(password));
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return enabled;
            }
        };
    }
}

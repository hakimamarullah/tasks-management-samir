package com.micro.fintech.model;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/28/2024 7:27 PM
@Last Modified 5/28/2024 7:27 PM
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.fintech.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;


    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column
    @JsonProperty("password")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column
    private Boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.password;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


}

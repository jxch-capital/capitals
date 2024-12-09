package io.github.jxch.capitals.cloud.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 500)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Authority> authorities;
}

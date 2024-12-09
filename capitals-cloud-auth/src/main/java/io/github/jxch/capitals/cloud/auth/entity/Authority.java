package io.github.jxch.capitals.cloud.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String authority;
}

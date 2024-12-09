package io.github.jxch.capitals.cloud.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "group_authorities")
public class GroupAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = false, length = 50)
    private String authority;
}

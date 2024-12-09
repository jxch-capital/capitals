package io.github.jxch.capitals.cloud.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "group_members")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
}

package com.example.userpermissionsystem.group.entity;

import com.example.userpermissionsystem.permission.entity.Permission;
import com.example.userpermissionsystem.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "`groups`")  // add backticks around groups to escape it
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Many-to-Many with Permission
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_permissions",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    // Many-to-Many with User
    @ManyToMany(mappedBy = "groups")
    private Set<User> users;
}

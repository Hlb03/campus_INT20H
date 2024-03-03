package com.final_proj.final_proj.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_in_group", schema = "public")
public class UsersGroup {

    @Id
    @SequenceGenerator(name = "users_in_group_id_seq", sequenceName = "users_in_group_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "users_in_group_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @Enumerated(value = EnumType.STRING)
    private Course course;
}

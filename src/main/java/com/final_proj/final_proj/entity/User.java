package com.final_proj.final_proj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "user", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email;
    @Id
    @SequenceGenerator(name = "user_user_id_seq", sequenceName = "user_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_user_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "position_name")
    private String positionName;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}

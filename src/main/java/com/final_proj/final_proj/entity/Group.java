package com.final_proj.final_proj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "group", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq")
    @GeneratedValue(generator = "group_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_cipher")
    private String cipher;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
    private Faculty faculty;
}

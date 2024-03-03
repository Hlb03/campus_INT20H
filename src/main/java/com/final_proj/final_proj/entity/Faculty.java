package com.final_proj.final_proj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Id
    @SequenceGenerator(name = "faculty_id_seq", sequenceName = "faculty_id_seq")
    @GeneratedValue(generator = "faculty_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "faculty_id")
    private Long id;

    @Column(name = "faculty_name")
    private String name;
}

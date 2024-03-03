package com.final_proj.final_proj.entity.tests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "subject", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @SequenceGenerator(name = "subject_id_seq", sequenceName = "subject_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "subject_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
}

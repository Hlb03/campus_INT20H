package com.final_proj.final_proj.entity.tests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "test_option", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class TestOption {

    @Id
    @SequenceGenerator(name = "test_option_id_seq", sequenceName = "test_option_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "test_option_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String question;
    @Column(name = "option_1")
    private String option1;
    @Column(name = "option_2")
    private String option2;
    @Column(name = "option_3")
    private String option3;
    @Column(name = "option_4")
    private String option4;

    @Column(name = "correct_option")
    private Byte correctOption;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;
}

package com.final_proj.final_proj.entity.tests;

import com.final_proj.final_proj.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "student_test_attempts", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class StudentTestAttempts {

    @Id
    @SequenceGenerator(name = "student_test_attempts_id_seq", sequenceName = "student_test_attempts_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "student_test_attempts_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal score;
    @Column(name = "passed_at")
    private LocalDateTime passedAt;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    private User user;
}

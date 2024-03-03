package com.final_proj.final_proj.entity.tests;

import com.final_proj.final_proj.entity.Group;
import com.final_proj.final_proj.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "test", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @SequenceGenerator(name = "test_id_seq", sequenceName = "test_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "test_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String theme;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    private User creator;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;
}

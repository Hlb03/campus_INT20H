package com.final_proj.final_proj.entity.message_types;

import com.final_proj.final_proj.entity.Course;
import com.final_proj.final_proj.entity.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "course_message", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class CourseMessage {

    @Id
    @SequenceGenerator(name = "course_message_id_seq", sequenceName = "course_message_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "course_message_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;
}

package com.final_proj.final_proj.entity.message_types;

import com.final_proj.final_proj.entity.Faculty;
import com.final_proj.final_proj.entity.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "faculty_message", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class FacultyMessage {

    @Id
    @SequenceGenerator(name = "faculty_message_id_seq", sequenceName = "faculty_message_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "faculty_message_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;
}

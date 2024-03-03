package com.final_proj.final_proj.entity.message_types;

import com.final_proj.final_proj.entity.Group;
import com.final_proj.final_proj.entity.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "group_message", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessage {

    @Id
    @SequenceGenerator(name = "group_message_id_seq", sequenceName = "group_message_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "group_message_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;
}

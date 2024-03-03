package com.final_proj.final_proj.entity.message_types;

import com.final_proj.final_proj.entity.Message;
import com.final_proj.final_proj.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "individual_message", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class IndividualMessage {

    @Id
    @SequenceGenerator(name = "individual_message_id_seq", sequenceName = "individual_message_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "individual_message_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;
}

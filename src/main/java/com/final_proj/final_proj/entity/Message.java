package com.final_proj.final_proj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "message", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "message_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;
}

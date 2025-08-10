package com.karthik.visionlabs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "session_id" , referencedColumnName = "sessionId")
    private LiveSession session;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;

    private String questionText;
    private LocalTime askedAt;
    private LocalTime timestampInVideo;
}

package com.karthik.visionlabs.entities;

import com.karthik.visionlabs.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class LiveSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;
    private LocalDateTime ScheduledAt;
    private String streamUrl;
    @Enumerated(EnumType.STRING)
    private Status  status;

}

package com.karthik.visionlabs.entities;

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
@Table(name = "transcripts")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transcriptId;

    @ManyToOne
    @JoinColumn(name = "session_id",referencedColumnName = "sessionId")
    public LiveSession session;
    private String transcriptText;
    private LocalDateTime generatedAt;
}

package com.karthik.visionlabs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "summaries")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long summaryId;

    @ManyToOne
    @JoinColumn(name = "session_id" , referencedColumnName = "sessionId")
    private LiveSession session;

    private Boolean generatedByAi;
    private String summaryText;
    private String pdfUrl;
}

package com.karthik.visionlabs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookmarks")
public class Bookmarks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookmarkId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "session_id" ,referencedColumnName = "sessionId")
    private LiveSession session;
    private LocalTime timestampInVideo;
    private String note;
}

package com.karthik.visionlabs.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class createStreamRequestDTO {
    private String title;
    private String instructor;
    private String scheduledTime;
    private int durationMinutes;
}

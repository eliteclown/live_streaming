package com.karthik.visionlabs.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class createStreamResponseDTO {
    private String streamId;
    private String streamUrl;
    private String status;
}

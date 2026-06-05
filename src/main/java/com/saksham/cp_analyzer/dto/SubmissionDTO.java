package com.saksham.cp_analyzer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDTO {

    private Long id;
    private String verdict;
    private String language;
    private Integer runtime;
    private Integer memory;
    private LocalDateTime submissionTime;
    private String problemName;
}
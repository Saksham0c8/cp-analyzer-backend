package com.saksham.cp_analyzer.dto;

public class TopicStrengthDTO {

    private Long attempted;

    private Long solved;

    private Double accuracy;

    public TopicStrengthDTO() {
    }

    public TopicStrengthDTO(
            Long attempted,
            Long solved,
            Double accuracy
    ) {
        this.attempted = attempted;
        this.solved = solved;
        this.accuracy = accuracy;
    }

    public Long getAttempted() {
        return attempted;
    }

    public void setAttempted(Long attempted) {
        this.attempted = attempted;
    }

    public Long getSolved() {
        return solved;
    }

    public void setSolved(Long solved) {
        this.solved = solved;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }
}

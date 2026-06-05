package com.saksham.cp_analyzer.dto;

public class ActivityStatsDTO {

    private Long last7Days;

    private Long last30Days;

    private Long activeDays;

    public ActivityStatsDTO() {
    }

    public ActivityStatsDTO(
            Long last7Days,
            Long last30Days,
            Long activeDays
    ) {
        this.last7Days = last7Days;
        this.last30Days = last30Days;
        this.activeDays = activeDays;
    }

    public Long getLast7Days() {
        return last7Days;
    }

    public void setLast7Days(Long last7Days) {
        this.last7Days = last7Days;
    }

    public Long getLast30Days() {
        return last30Days;
    }

    public void setLast30Days(Long last30Days) {
        this.last30Days = last30Days;
    }

    public Long getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(Long activeDays) {
        this.activeDays = activeDays;
    }
}

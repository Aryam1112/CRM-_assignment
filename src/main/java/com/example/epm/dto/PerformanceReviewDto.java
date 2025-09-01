package com.example.epm.dto;

import java.time.LocalDate;

public record PerformanceReviewDto(Long id, LocalDate reviewDate, Integer score, String reviewComments) { }

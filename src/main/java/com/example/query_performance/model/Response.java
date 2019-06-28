package com.example.query_performance.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {

    private ServiceStatus status;
    private PerformanceCheckResult checkResult;
    private String errorMessage;

}

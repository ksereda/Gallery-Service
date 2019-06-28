package com.example.query_performance.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class PerformanceCheckResult {

    private Long checkStartedAt;
    private Long checkEndedAt;
    private Long totalCheckDurationTime;
    List<QueryExecutionResult> queryExecutionResults;

}

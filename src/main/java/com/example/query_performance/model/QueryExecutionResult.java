package com.example.query_performance.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QueryExecutionResult {

    private String databaseName;
    private Long queryExecutionStartedAt;
    private Long queryExecutionEndedAt;
    private Long queryExecutionDurationTime;

}

package com.example.query_performance.service;

import com.example.query_performance.model.PerformanceCheckResult;

public interface DBService {

    PerformanceCheckResult doCheck(String query);

}

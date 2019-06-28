package com.example.query_performance.service;

import com.example.query_performance.config.DatabaseConfig;
import com.example.query_performance.model.PerformanceCheckResult;
import com.example.query_performance.model.QueryExecutionResult;
import com.example.query_performance.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Log
@RequiredArgsConstructor
public class DBServiceImpl implements DBService {

    private final DatabaseConfig databaseConfig;

    private Map<String, JdbcTemplate> templates;

    @PostConstruct
    public void init() {
        templates = databaseConfig.getDatabases()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> createJdbcTemplate(entry.getValue())));
    }

    private JdbcTemplate createJdbcTemplate(DatabaseConfig.Database database) {
        return new JdbcTemplate(DataSourceBuilder
                .create()
                .url(database.getUrl())
                .username(database.getUser())
                .password(database.getPassword())
                .driverClassName(database.getDriverClassName())
                .build());
    }

    public synchronized PerformanceCheckResult doCheck(String query) {

        PerformanceCheckResult checkResult = PerformanceCheckResult
                .builder()
                .queryExecutionResults(new ArrayList<>())
                .checkStartedAt(System.currentTimeMillis())
                .build();

        List<Future<QueryExecutionResult>> futureExecutionResults = templates
                .entrySet()
                .stream()
                .map(entry -> Executors.newSingleThreadExecutor().submit(() -> performQuery(entry.getKey(), entry.getValue(), query)))
                .collect(Collectors.toList());

        log.info("Thread " + futureExecutionResults + " start in: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        checkResult.setQueryExecutionResults(futureExecutionResults.stream().map(this::getResultFromFuture).collect(Collectors.toList()));
        checkResult.setCheckEndedAt(System.currentTimeMillis());
        checkResult.setTotalCheckDurationTime(CommonUtils.getDurationMillis(checkResult.getCheckStartedAt(), checkResult.getCheckEndedAt()));

        return checkResult;
    }

    private QueryExecutionResult performQuery(String databaseName, JdbcTemplate template, String query) {
        QueryExecutionResult executionResult = QueryExecutionResult
                .builder()
                .databaseName(databaseName)
                .queryExecutionStartedAt(System.currentTimeMillis())
                .build();
        template.query(query, ResultSet::getRow);
//        randomSleep();
        executionResult.setQueryExecutionEndedAt(System.currentTimeMillis());
        executionResult.setQueryExecutionDurationTime(
                CommonUtils.getDurationMillis(executionResult.getQueryExecutionStartedAt(), executionResult.getQueryExecutionEndedAt()));
        return executionResult;
    }

    @SneakyThrows
    private QueryExecutionResult getResultFromFuture(Future<QueryExecutionResult> resultFuture) {
        if (!resultFuture.isDone()) {
            log.info("Retry get future: " + resultFuture + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
        QueryExecutionResult result = resultFuture.get();
        log.info("Successfully get future: " + resultFuture);
        return result;
    }

    private void randomSleep() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(1000 * (random.nextInt(5) + 1));
    }

}

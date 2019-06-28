package com.example.query_performance.controller;

import com.example.query_performance.exception.ExceptionHandlerController;
import com.example.query_performance.model.Request;
import com.example.query_performance.model.Response;
import com.example.query_performance.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.example.query_performance.model.ServiceStatus.AVAILABLE;
import static com.example.query_performance.model.ServiceStatus.ERROR;

@RestController
@RequiredArgsConstructor
public class QueryController extends ExceptionHandlerController {

    private final DBService dbService;

    @ResponseBody
    @PostMapping(value = "/select/")
    public Response callSelectQuery(@RequestBody Request request) {
        return startCheck(request.getQuery());
    }

    private Response startCheck(String query) {
        Response response = Response.builder().build();
        try {
            response.setCheckResult(dbService.doCheck(query));
            response.setStatus(AVAILABLE);
        } catch (Throwable e) {
            response.setStatus(ERROR);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

}

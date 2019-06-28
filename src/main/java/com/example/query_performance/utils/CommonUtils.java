package com.example.query_performance.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CommonUtils {

    public static Long getDurationMillis(Long start, Long end) {
        return end - start;
    }

}

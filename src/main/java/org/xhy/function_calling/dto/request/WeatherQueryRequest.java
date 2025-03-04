package org.xhy.function_calling.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 天气查询请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherQueryRequest {
    private String city;
    private String date;
}
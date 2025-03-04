package org.xhy.function_calling.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应格式配置
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFormat {
    private String type;
}
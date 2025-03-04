package org.xhy.function_calling.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工具定义
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tool {
    private String type;
    private ToolFunction function;
}
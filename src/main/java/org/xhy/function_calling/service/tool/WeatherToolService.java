package org.xhy.function_calling.service.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xhy.function_calling.dto.request.WeatherQueryRequest;
import org.xhy.function_calling.dto.response.WeatherData;
import org.xhy.function_calling.util.JsonUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 天气服务
 * 模拟天气数据查询
 */
@Service
public class WeatherToolService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherToolService.class);
    private static final Map<String, String> WEATHER_CONDITIONS = new HashMap<>();
    private static final Random random = new Random();

    static {
        // 初始化天气状况
        WEATHER_CONDITIONS.put("sunny", "晴天");
        WEATHER_CONDITIONS.put("cloudy", "多云");
        WEATHER_CONDITIONS.put("rainy", "雨天");
        WEATHER_CONDITIONS.put("snowy", "雪天");
        WEATHER_CONDITIONS.put("windy", "大风");
        WEATHER_CONDITIONS.put("foggy", "雾天");
    }

    /**
     * 获取天气数据
     * 
     * @param functionArguments 函数参数 JSON 字符串
     * @return 天气数据
     */
    public WeatherData getWeatherData(String functionArguments) {
        try {
            // 解析函数参数
            WeatherQueryRequest request = JsonUtils.fromJson(functionArguments, WeatherQueryRequest.class);

            if (request == null || request.getCity() == null || request.getCity().trim().isEmpty()) {
                logger.error("无效的天气查询请求: {}", functionArguments);
                return null;
            }

            // 处理日期，如果为空则使用今天
            String date = request.getDate();
            if (date == null || date.trim().isEmpty()) {
                date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            }

            // 模拟天气数据
            return simulateWeatherData(request.getCity(), date);

        } catch (Exception e) {
            logger.error("处理天气查询请求失败", e);
            return null;
        }
    }

    /**
     * 模拟天气数据
     * 
     * @param city 城市
     * @param date 日期
     * @return 模拟的天气数据
     */
    private WeatherData simulateWeatherData(String city, String date) {
        // 获取随机天气状况
        String[] conditions = WEATHER_CONDITIONS.values().toArray(new String[0]);
        String condition = conditions[random.nextInt(conditions.length)];

        // 生成随机温度 (5-35°C)
        int temperature = 5 + random.nextInt(31);

        // 生成随机湿度 (30-90%)
        int humidity = 30 + random.nextInt(61);

        // 生成随机风速 (0-30 km/h)
        int windSpeed = random.nextInt(31);

        return WeatherData.builder()
                .city(city)
                .date(date)
                .condition(condition)
                .temperature(temperature + "°C")
                .humidity(humidity + "%")
                .windSpeed(windSpeed + " km/h")
                .build();
    }
}
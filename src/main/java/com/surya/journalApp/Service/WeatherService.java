package com.surya.journalApp.Service;

import com.surya.journalApp.api.response.WeatherResponse;
import com.surya.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apikey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {

        // ✅ FIX 1: Normalize key (VERY IMPORTANT)
        String key = "weather_of_" + city.trim().toLowerCase();

        WeatherResponse weatherResponse =
                redisService.get(key, WeatherResponse.class);

        if (weatherResponse != null) {
            System.out.println("🔥 CACHE HIT");
            return weatherResponse;
        }

        System.out.println("❌ CACHE MISS → calling API");

        String finalApi = appCache.APP_CACHE.get("WEATHER_API")
                .replace("<apiKey>", apikey)
                .replace("<city>", city);

        try {
            ResponseEntity<WeatherResponse> response =
                    restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);

            WeatherResponse body = response.getBody();

            // ✅ FIX 2: Add debug logs
            if (body == null) {
                System.out.println("❌ API returned NULL");
                return null;
            }

            System.out.println("✅ API returned data → saving to Redis");

            // ✅ FIX 3: Save with SAME key
            redisService.set(key, body, 300L);

            return body;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
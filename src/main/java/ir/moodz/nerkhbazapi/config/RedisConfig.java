package ir.moodz.nerkhbazapi.config;

import ir.moodz.nerkhbazapi.domain.Currency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(ObjectMapper objectMapper) {
        JavaType cacheValueType = objectMapper.getTypeFactory()
                .constructCollectionType(
                        List.class,
                        Currency.class
                );

        JacksonJsonRedisSerializer<Currency> serializer =
                new JacksonJsonRedisSerializer<>(
                        objectMapper,
                        cacheValueType
                );

        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
    }
}

package org.terrence.testapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.terrence.testapp.model.Person;
import org.terrence.testapp.repo.PersonRepository;

@Configuration
public class RedisConfig {

    @Bean
    public PersonRepository redisRepository(RedisTemplate<String, Person> redisTemplate) {
        return new PersonRepository(redisTemplate);
    }

    @Bean
    public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Person> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Person> albumSerializer = new Jackson2JsonRedisSerializer<>(Person.class);

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(albumSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(albumSerializer);

        return template;
    }

}
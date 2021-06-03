package com.example.init.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

/**
 * 开启自动化配置 Spring Session 使用 Redis 作为数据源。
 *
 * 该注解有如下属性：
 * maxInactiveIntervalInSeconds:设置 Session 的失效时间，单位为秒。默认1800秒。
 * redisNamespace:为键定义统一的命名前缀。默认:spring:session
 * redisFlushMode:Redis 会话刷新模式(RedisFlushMode)。目前有两种，默认为 RedisFlushMode.ON_SAVE 。
 * cleanupCron:清理 Redis Session 会话过期的任务执行 CRON 表达式，默认为 "0 * * * * *" 每分钟执行一次。虽然说，Redis 自带了 key 的过期，但是惰性删除策略，实际过期的 Session 还在 Redis 中占用内存。所以，Spring Session 通过定时任务，删除 Redis 中过期的 Session ，尽快释放 Redis 的内存。
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
@Configuration
public class SpringSessionConfig {

    /**
     * 序列化器
     * 创建 {@link RedisOperationsSessionRepository} 使用的 RedisSerializer Bean 。
     *
     * 具体可以看看 {@link RedisHttpSessionConfiguration#setDefaultRedisSerializer(RedisSerializer)} 方法，
     * 它会引入名字为 "springSessionDefaultRedisSerializer" 的 Bean 。
     *
     * @return RedisSerializer Bean
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }

}
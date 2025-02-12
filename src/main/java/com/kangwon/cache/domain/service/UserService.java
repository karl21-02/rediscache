package com.kangwon.cache.domain.service;

import com.kangwon.cache.domain.entity.RedisHashUser;
import com.kangwon.cache.domain.entity.User;
import com.kangwon.cache.domain.repository.RedisHashUserRepository;
import com.kangwon.cache.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.kangwon.cache.config.CacheConfig.CACHE1;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisHashUserRepository redisHashUserRepository;
    private final RedisTemplate<String, User> userRedisTemplate;
    private final RedisTemplate<String, Object> objectRedisTemplate;

    public User getUser(final Long id) {
        var key = "users:%d".formatted(id);
        // 1. cache get
        var cachedUser = objectRedisTemplate.opsForValue().get(key);
        if(cachedUser != null) {
            return (User)cachedUser;
        }

        // 2. else db -> cache set
        User user = userRepository.findById(id).orElseThrow();
        objectRedisTemplate.opsForValue().set(key, user, Duration.ofSeconds(30));
        return user;
    }

    /**
     * redisHash 사용
     * @param id
     * @return
     */
    public RedisHashUser getUser2(final Long id) {
        // redis 같이 있으면 리턴
        // 없으면 db 값 활용
        var cachedUser = redisHashUserRepository.findById(id).orElseGet(() -> {
            User user = userRepository.findById(id).orElseThrow();
            return redisHashUserRepository.save(RedisHashUser.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .createTime(user.getCreatedAt())
                    .updateTime(user.getUpdatedAt())
                    .build());
        });
        return cachedUser;
    }

    /**
     * CacheEvict 등등..
     * @param id
     * @return
     */
    @Cacheable(cacheNames = CACHE1, key = "'user:' + #id")
    public User getUser3(final Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}

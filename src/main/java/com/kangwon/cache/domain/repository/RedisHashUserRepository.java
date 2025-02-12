package com.kangwon.cache.domain.repository;

import com.kangwon.cache.domain.entity.RedisHashUser;
import org.springframework.data.repository.CrudRepository;

public interface RedisHashUserRepository extends CrudRepository<RedisHashUser, Long> {

}
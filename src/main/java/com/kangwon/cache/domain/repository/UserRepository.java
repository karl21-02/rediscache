package com.kangwon.cache.domain.repository;


import com.kangwon.cache.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

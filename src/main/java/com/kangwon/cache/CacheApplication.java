package com.kangwon.cache;

import com.kangwon.cache.domain.entity.User;
import com.kangwon.cache.domain.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class CacheApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        userRepository.save(User.builder().name("noa").email("noa@gmail.com").build());
//        userRepository.save(User.builder().name("bob").email("bob@gmail.com").build());
//        userRepository.save(User.builder().name("greg").email("greg@gmail.com").build());

    }
}

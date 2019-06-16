package com.laba.store.Controllers.repos;

import com.laba.store.Controllers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

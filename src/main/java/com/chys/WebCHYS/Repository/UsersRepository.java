package com.chys.WebCHYS.Repository;

import com.chys.WebCHYS.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

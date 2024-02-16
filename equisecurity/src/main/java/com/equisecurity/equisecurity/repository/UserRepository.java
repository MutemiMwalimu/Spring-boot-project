package com.equisecurity.equisecurity.repository;


import com.equisecurity.equisecurity.model.Role;
import com.equisecurity.equisecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}

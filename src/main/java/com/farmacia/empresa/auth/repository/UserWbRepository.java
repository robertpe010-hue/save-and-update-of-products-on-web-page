package com.farmacia.empresa.auth.repository;

import com.farmacia.empresa.auth.entity.UserWb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWbRepository extends JpaRepository<UserWb, Long> {

    Optional<UserWb> findByEmail(String email);

    boolean existsByEmail(String email);

}

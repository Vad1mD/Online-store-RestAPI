package com.onlineshop.onlineshop.persistance;

import com.onlineshop.onlineshop.dataModels.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findAll();

    Optional<Users> findById(Long id);
}

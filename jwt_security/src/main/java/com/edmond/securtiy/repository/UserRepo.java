package com.edmond.securtiy.repository;

import com.edmond.securtiy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    void deleteByEmail(String email);
}

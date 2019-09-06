package com.mosaiker.adminservice.repository;

import com.mosaiker.adminservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByUIdIn(List<Long> uIds);
}


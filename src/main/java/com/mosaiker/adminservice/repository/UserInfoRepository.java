package com.mosaiker.adminservice.repository;

import com.mosaiker.adminservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends JpaRepository<User, Long> {

  User findUserByUId(Long uid);

}


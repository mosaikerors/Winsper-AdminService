package com.mosaiker.adminservice.service;

import com.mosaiker.adminservice.repository.UserInfoRepository;
import com.mosaiker.adminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImple implements UserInfoService {

  @Autowired
  private UserInfoRepository userInfoRepository;

  @Override
  public List<User> findAll() {
    return userInfoRepository.findAll();
  }

  @Override
  public User findUserByUId(Long id) {
    return userInfoRepository.findUserByUId(id);
  }
  @Override
  public User update(User user) {
    return userInfoRepository.save(user);
  }

}

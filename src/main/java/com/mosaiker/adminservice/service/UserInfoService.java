package com.mosaiker.adminservice.service;
import com.mosaiker.adminservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userInfoService")
public interface UserInfoService {

  List<User> findAll();

  User findUserByUId(Long uId);

  User update(User user);
}

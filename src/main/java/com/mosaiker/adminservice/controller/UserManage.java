package com.mosaiker.adminservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mosaiker.adminservice.service.UserInfoService;
import com.mosaiker.adminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/admin")
public class UserManage {

  @Autowired
  private UserInfoService userInfoService;

  @RequestMapping(value = "/userList", method = RequestMethod.GET)
  public JSONObject findAllUser() {
    JSONObject result = new JSONObject();
    JSONArray userArray = new JSONArray();
    List<User> users = userInfoService.findAll();
    if (users != null) {
      for(User user : users) {
        JSONObject oneUser = new JSONObject();
        oneUser.put("uId", user.getUId());
        oneUser.put("username",user.getUsername());
        oneUser.put("phone",user.getPhone());
        oneUser.put("status",user.getStatus());
        userArray.add(oneUser);
      }
      result.put("userList",userArray);
      result.put("message", "ok");
      return result;
    } else {
      result.put("message", "Oops,尚未有其他用户");
      return result;
    }
  }

  @RequestMapping(value = "/manage", method = RequestMethod.PUT)
  public JSONObject changeStatus(@RequestBody JSONObject param) {
    JSONObject result = new JSONObject();
    Long uId = Long.parseLong(param.getString("uId"));
    User user = userInfoService.findUserByUId(uId);
    if (user != null) {
      int i = -user.getStatus();
      user.setStatus(i);
      userInfoService.update(user);
      result.put("status", i);
      result.put("message", "ok");
      return result;
    } else {
      result.put("message", "无法获取该用户信息");
      return result;
    }
  }

}

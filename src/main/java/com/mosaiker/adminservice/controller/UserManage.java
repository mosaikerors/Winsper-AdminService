package com.mosaiker.adminservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mosaiker.adminservice.service.UserInfoService;
import com.mosaiker.adminservice.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/admin")
@Api(description = "管理员服务接口，仅能查询所有用户和解禁/封禁操作.")
public class UserManage {

  @Autowired
  private UserInfoService userInfoService;

  @RequestMapping(value = "/userList", method = RequestMethod.GET)
  @ApiOperation(value="返回系统中所有用户（不显示其他管理员）",produces = "application/json")
  @ApiImplicitParams({})
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

  @RequestMapping(value = "/manage", method = RequestMethod.POST)
  @ApiOperation(value="返回用户当前状态，1为普通用户，2为会员，对应的负数表示已封禁",produces = "application/json")
public JSONObject changeStatus(
  @ApiParam(name="uId",value="用户ID",type = "long",example = "123456") @RequestBody JSONObject param) {
    Long uId = param.getLong("uId");
    JSONObject result = new JSONObject();
    User user = userInfoService.findUserByUId(uId);
    if (user != null && user.getStatus()!=100) {
      int i = -user.getStatus();
      user.setStatus(i);
      userInfoService.update(user);
      result.put("status", i);
      result.put("message", "ok");
      return result;
    } else if(user==null){
      result.put("message", "无法获取该用户信息");
      return result;
    }
    else{
      result.put("message","无法对管理员进行操作");
      return result;
    }
  }

}

package com.mosaiker.adminservice.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.alibaba.fastjson.JSONObject;
import com.mosaiker.adminservice.entity.User;
import com.mosaiker.adminservice.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * UserManage Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 2, 2019</pre>
 */
public class UserManageTest {

  private MockMvc mockMvc;
  @Mock
  private UserInfoService userInfoService;
  @InjectMocks
  private ManageController userManage;

  @Before
  public void before() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userManage).build();
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: findAllUser()
   */
  @Test
  public void testFindAllUser() throws Exception {
    List<User> mockList = new ArrayList<User>();
    mockList.add(new User(1000L, "username", "13011133377", 1));
    when(userInfoService.findAll()).thenReturn(mockList);
    String expected = "{\"userList\":[{\"uId\":1000,\"phone\":\"13011133377\",\"username\":\"username\",\"status\":1}],\"message\":\"ok\"}";
    mockMvc.perform(MockMvcRequestBuilders.get("/userList")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(expected))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    when(userInfoService.findAll()).thenReturn(null);
    String expectedNull = "{\"message\":\"Oops,尚未有其他用户\"}";
    mockMvc.perform(MockMvcRequestBuilders.get("/userList")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(expectedNull))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

  }

  /**
   * Method: changeStatus(@RequestParam JSONObject param)
   */
  @Test
  public void testChangeStatus() throws Exception {

    User oldUser = new User(1000L, "username", "13011133377", 1);
    User newUser = new User(1000L, "username", "13011133377", -1);
    when(userInfoService.findUserByUId(1000L)).thenReturn(oldUser);

    when(userInfoService.update(newUser)).thenReturn(newUser);

    String expected1 = "<JSONObject><message>ok</message><status>-1</status></JSONObject>";
    JSONObject mockParam = new JSONObject();
    mockParam.put("uId", "1000");
    mockMvc.perform(MockMvcRequestBuilders.put("/manage")
        .contentType(MediaType.APPLICATION_JSON).content(mockParam.toJSONString()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(expected1))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
    when(userInfoService.findUserByUId(2000L)).thenReturn(null);
    mockParam.clear();
    mockParam.put("uId", "2000");
    String expected2 = "<JSONObject><message>无法获取该用户信息</message></JSONObject>";
    mockMvc.perform(MockMvcRequestBuilders.put("/manage")
        .contentType(MediaType.APPLICATION_JSON).content(mockParam.toJSONString()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(expected2))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }


}

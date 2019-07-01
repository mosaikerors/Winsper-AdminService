package com.mosaiker.adminservice.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.mosaiker.adminservice.entity.User;
import com.mosaiker.adminservice.repository.UserInfoRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * UserInfoServiceImple Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 1, 2019</pre>
 */
public class UserInfoServiceImpleTest {

  @Mock
  private UserInfoRepository userInfoRepository;
  @InjectMocks
  private UserInfoServiceImple userInfoServiceImple;

  @Before
  public void before() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: findAll()
   */
  @Test
  public void testFindAll() throws Exception {
    User user1 = new User(10000, "username", "17037041703", 1);
    User user2 = new User(10001, "username", "17037031703", 1);
    List<User> userlist = Arrays.asList(user1, user2);
    // 设置模拟对象的返回预期值
    when(userInfoRepository.findAll()).thenReturn(userlist);
    // 执行测试
    List<User> userResult = userInfoServiceImple.findAll();
    // 验证模拟对象的fetchPerson(1)方法是否被调用了一次
    verify(userInfoRepository).findAll();
    assertEquals(userlist, userResult);
    // 检查模拟对象上是否还有未验证的交互
    verifyNoMoreInteractions(userInfoRepository);
  }

  @Test
  public void testFindUserByUId() throws Exception {
    User user = new User(10001, "username", "17037041703", 1);
    when(userInfoRepository.findUserByUId(10001L)).thenReturn(user);
    User result = userInfoServiceImple.findUserByUId(10001L);
    // 验证模拟对象的fetchPerson(1)方法是否被调用了一次
    assertThat(result, equalTo(user));
    // 检查模拟对象上是否还有未验证的交互

  }

  @Test
  public void testUpdate() throws Exception {
    User user = new User(10000, "username", "130111444777", 1);
    when(userInfoRepository.save(user)).thenReturn(user);
    User result = userInfoServiceImple.update(user);
    assertThat(user, equalTo(result));
  }
}

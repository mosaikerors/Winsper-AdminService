package com.mosaiker.adminservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Import(BeanValidatorPluginsConfiguration.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "向管理员展示的部分用户信息")
public class User {
  @Id
  private Long uId;
  private String username;
  private String phone;
  @Range(max = 200,min = -200)
  @ApiModelProperty(notes = "1表示普通用户，2表示会员,对应的负数表示被禁用", required = true, position = 3)
  private int status;

}

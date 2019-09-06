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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
  @Id
  private Long uId;
  private String username;
  private String phone;
  private int status;
  private Long firstin; //注册时间

}

package com.mosaiker.adminservice.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journal {

  @Id
  @GeneratedValue
  private Long journalId;// 手账 id
  private String journalUrl;

  public JSONObject ToJSONObject() {
    return new JSONObject() {{
      put("journalId", journalId);
      put("journalUrl", journalUrl);
    }};
  }
}

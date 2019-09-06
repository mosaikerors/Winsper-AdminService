package com.mosaiker.adminservice.entity;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hean {

  @Id
  private String hId;
  private Long uId;
  private Long createdTime; // 上传时间
  private String text;
  private double longtitude;//精度
  private double latitude;//纬度
  private double height;//海拔
  @ElementCollection
  private List<String> pictures;
  @ElementCollection
  private List<Long> likeUIds;
  @ElementCollection
  private List<Long> starUIds;
  @ElementCollection
  private List<String> commentIds;

  public Hean(Long uId, Long createdTime, String text, double longtitude, double latitude,
              double height, List<String> pictures) {
    this.uId = uId;
    this.createdTime = createdTime;
    this.text = text;
    this.longtitude = longtitude;
    this.latitude = latitude;
    this.height = height;
    this.pictures = pictures;
    this.likeUIds = new ArrayList<>();
    this.starUIds = new ArrayList<>();
    this.commentIds = new ArrayList<>();
  }

  public JSONObject ToJSONObject() {
    JSONObject result = new JSONObject(true);
    result.put("hId", this.hId);
    result.put("uId", this.uId);
    result.put("createdTime", this.createdTime);
    result.put("text", this.text);
    result.put("longtitude", this.longtitude);
    result.put("latitude", this.latitude);
    result.put("height", this.height);
    result.put("pictures", this.pictures);
    result.put("starCount", this.starUIds.size());
    result.put("likeCount", this.likeUIds.size());
    result.put("commentCount", this.commentIds.size());
    return result;
  }

  public JSONObject ToJSONObject(Long uId) {
    JSONObject result = new JSONObject(true);
    result.put("hId", this.hId);
    result.put("uId", this.uId);
    result.put("createdTime", this.createdTime);
    result.put("text", this.text);
    result.put("longtitude", this.longtitude);
    result.put("latitude", this.latitude);
    result.put("height", this.height);
    result.put("pictures", this.pictures);
    result.put("isLike", this.likeUIds.contains(uId));
    result.put("isStar", this.starUIds.contains(uId));
    return result;
  }

  public JSONObject ToJSONPoint() {
    JSONObject result = new JSONObject(100, true);
    result.put("hId", this.hId);
    result.put("longtitude", this.longtitude);
    result.put("latitude", this.latitude);
    result.put("height", this.height);
    return result;
  }

  public JSONObject ToCard(Long uId) {
    JSONObject result = new JSONObject(true);
    result.put("hId", this.hId);
    result.put("likeCount", this.likeUIds.size());
    result.put("starCount", this.starUIds.size());
    result.put("commentCount", this.commentIds.size());
    result.put("text", this.text);
    if (this.pictures.size() > 0) {
      result.put("cover", this.pictures.get(0));
    }
    result.put("hasLiked", this.likeUIds.contains(uId));
    result.put("hasStared", this.starUIds.contains(uId));
    return result;
  }

  public JSONObject ToDetail(Long uId) {
    JSONObject result = new JSONObject(true);

    result.put("hId", this.hId);
    result.put("uId", this.uId);
    result.put("createdTime", this.createdTime);
    result.put("pictures", this.pictures);
    result.put("likeCount", this.likeUIds.size());
    result.put("starCount", this.starUIds.size());
    result.put("hasLiked", this.likeUIds.contains(uId));
    result.put("hasStared", this.starUIds.contains(uId));

    return result;
  }

  public void addComment(String cId) {
    this.commentIds.add(cId);
  }

  public Long getUId() {
    return uId;
  }

  public List<Long> getLikeUIds() {
    return this.likeUIds;
  }

  public List<Long> getStarUIds() {
    return this.starUIds;
  }
}

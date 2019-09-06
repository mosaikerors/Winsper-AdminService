package com.mosaiker.adminservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mosaiker.adminservice.entity.Contribution;
import com.mosaiker.adminservice.entity.Hean;
import com.mosaiker.adminservice.entity.Journal;
import com.mosaiker.adminservice.entity.User;
import com.mosaiker.adminservice.repository.ContributionRepository;
import com.mosaiker.adminservice.repository.HeanRepository;
import com.mosaiker.adminservice.repository.JournalRepository;
import com.mosaiker.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ManageController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JournalRepository journalRepository;

  @Autowired
  private HeanRepository heanRepository;

  @Autowired
  private ContributionRepository contributionRepository;

  @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
  public JSONObject getAllUser() {
    JSONObject result = new JSONObject();
    JSONArray userArray = new JSONArray();
    List<User> users = userRepository.findAll();
    if (users == null) {
      users = new ArrayList<>();
    }
    for(User user : users) {
      JSONObject oneUser = new JSONObject();
      oneUser.put("uId", user.getUId());
      oneUser.put("username",user.getUsername());
      oneUser.put("phone",user.getPhone());
      oneUser.put("status",user.getStatus());
      oneUser.put("firstin",user.getFirstin());
      userArray.add(oneUser);
    }
    result.put("data",userArray);
    result.put("rescode", 0);
    return result;
  }

  @RequestMapping(value = "/getAllHean", method = RequestMethod.GET)
  public JSONObject getAllHean() {
    JSONObject result = new JSONObject();
    JSONArray heanArray = new JSONArray();
    List<Hean> heans = heanRepository.findAll();
    if (heans == null) {
      heans = new ArrayList<>();
    }
    for(Hean hean : heans) {
      heanArray.add(hean.ToJSONObject());
    }
    result.put("data",heanArray);
    result.put("rescode", 0);
    return result;
  }

  @RequestMapping(value = "/getAllJournal", method = RequestMethod.GET)
  public JSONObject getAllJournal() {
    JSONObject result = new JSONObject();
    JSONArray journalArray = new JSONArray();
    List<Journal> journals = journalRepository.findAll();
    if (journals == null) {
      journals = new ArrayList<>();
    }
    for(Journal journal : journals) {
      journalArray.add(journal.ToJSONObject());
    }
    result.put("data",journalArray);
    result.put("rescode", 0);
    return result;
  }

  @RequestMapping(value = "/getAllSubmission", method = RequestMethod.GET)
  public JSONObject getAllSubmission() {
    JSONObject result = new JSONObject();
    JSONArray contributionArray = new JSONArray();
    List<Contribution> contributions = contributionRepository.findAll();
    if (contributions == null) {
      contributions = new ArrayList<>();
    }
    for(Contribution contribution : contributions) {
      contributionArray.add(contribution.ToJSONObject());
    }
    result.put("data",contributionArray);
    result.put("rescode", 0);
    return result;
  }

  @RequestMapping(value = "/check", method = RequestMethod.PUT)
  public JSONObject checkSubmission(@RequestBody JSONObject params) {
    Contribution contribution = contributionRepository.findByCId(params.getLong("cId"));
    contribution.setStatus(params.getIntValue("status"));
    contributionRepository.save(contribution);
    JSONObject result = new JSONObject();
    result.put("rescode", 0);
    return result;
  }

  @RequestMapping(value = "/enable", method = RequestMethod.PUT)
  public JSONObject enableUsers(@RequestBody JSONObject params) {
    List<Long> uIds = params.getJSONArray("user").toJavaList(Long.class);
    List<User> users = userRepository.findUsersByUIdIn(uIds);
    for (User user : users) {
      int status = user.getStatus();
      if (status < 0) {
        status *= -1;
        user.setStatus(status);
        userRepository.save(user);
      }
    }
    JSONObject result = new JSONObject();
    result.put("rescode", 0);
    return result;
  }

  @RequestMapping(value = "/disable", method = RequestMethod.PUT)
  public JSONObject disableUsers(@RequestBody JSONObject params) {
    List<Long> uIds = params.getJSONArray("user").toJavaList(Long.class);
    List<User> users = userRepository.findUsersByUIdIn(uIds);
    for (User user : users) {
      int status = user.getStatus();
      if (status > 0) {
        status *= -1;
        user.setStatus(status);
        userRepository.save(user);
      }
    }
    JSONObject result = new JSONObject();
    result.put("rescode", 0);
    return result;
  }
}

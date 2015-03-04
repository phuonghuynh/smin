package com.smin.dto;

/**
 * Created by phuonghqh on 2/15/15.
 */
public class CompanyInfo {
  private String name;

  private String desc;

  private String type;

  public CompanyInfo() {
  }

  public CompanyInfo(String name, String desc, String type) {
    this.name = name;
    this.desc = desc;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

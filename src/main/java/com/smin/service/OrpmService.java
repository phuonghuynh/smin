package com.smin.service;

import com.smin.dto.CompanyInfo;
import com.smin.dto.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by phuonghqh on 3/3/15.
 */
@Service("ORPMService")
public class OrpmService implements SService {

  @Value("${orpm.link}")
  private String link;

  @Value("${sudo.pwd}")
  private String sudoPwd;

//  @Value("${soft.path}")
//  private String softPath;

  public void doService(CompanyInfo companyInfo) {
//    String[] cmd = {"sh", "-c", "echo '" + sudoPwd + "'| sudo -S " + "cp -R " + softPath + "orpm"};


//    Result result = new Result(200, "Done! Please go to link " + String.format(link, companyInfo.getName()), null);
//    return result;
  }
}

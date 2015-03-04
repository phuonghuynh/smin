package com.smin.service;

import com.smin.dto.CompanyInfo;
import com.smin.entity.Company;
import org.javalite.activejdbc.Base;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuonghqh on 2/15/15.
 */
@Service
public class CompanyService {

  @Value("${db.host}")
  private String dbHost;

  @Value("${db.username}")
  private String dbUsername;

  @Value("${db.password}")
  private String dbPassword;

  @Value("${db.driverClass}")
  private String dbDriverClass;

  @Resource
  private ApplicationContext applicationContext;

  public List<CompanyInfo> findAll() {
    List<CompanyInfo> companyInfos = new ArrayList<>();
    Base.open(dbDriverClass, dbHost, dbUsername, dbPassword);
    List<Company> companies = Company.findAll();
    for (Company company : companies) {
      CompanyInfo companyInfo = new CompanyInfo(company.get("_name").toString(), company.get("_desc").toString(), company.get("_type").toString());
      companyInfos.add(companyInfo);
    }
    Base.close();
    return companyInfos;
  }

  public void register(CompanyInfo companyInfo) {
    SService service = applicationContext.getBean(companyInfo.getType() + "Service", SService.class);
    service.doService(companyInfo);
//    save(companyInfo);
  }

  private void save(CompanyInfo companyInfo) {
    Base.open(dbDriverClass, dbHost, dbUsername, dbPassword);
    Company company = new Company();
    company.set(new String[]{"_name", "_desc", "_type"}, new String[]{companyInfo.getName(), companyInfo.getDesc(), companyInfo.getType()});
    company.saveIt();
    Base.close();
  }
}

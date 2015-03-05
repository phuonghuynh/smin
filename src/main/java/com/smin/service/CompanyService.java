package com.smin.service;

import com.smin.dto.CompanyInfo;
import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.IIOImage;

/**
 * Created by phuonghqh on 2/15/15.
 */
@Service
public class CompanyService {

  private final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

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

//  public List<CompanyInfo> findAll() {
//    List<CompanyInfo> companyInfos = new ArrayList<>();
//    Base.open(dbDriverClass, dbHost, dbUsername, dbPassword);
//    List<Company> companies = Company.findAll();
//    for (Company company : companies) {
//      CompanyInfo companyInfo = new CompanyInfo(company.get("_name").toString(), company.get("_desc").toString(), company.get("_type").toString());
//      companyInfos.add(companyInfo);
//    }
//    Base.close();
//    return companyInfos;
//  }

  public void register(CompanyInfo companyInfo) {
    SService service = applicationContext.getBean(companyInfo.getType() + "Service", SService.class);
    try {
      service.doService(companyInfo);
    }
    catch (Exception e) {
      LOGGER.error("Error: ", e);
    }
//    save(companyInfo);
  }

//  private void save(CompanyInfo companyInfo) {
//    Base.open(dbDriverClass, dbHost, dbUsername, dbPassword);
//    Company company = new Company();
//    company.set(new String[]{"_name", "_desc", "_type"}, new String[]{companyInfo.getName(), companyInfo.getDesc(), companyInfo.getType()});
//    company.saveIt();
//    Base.close();
//  }
}

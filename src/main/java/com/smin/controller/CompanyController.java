package com.smin.controller;

import com.smin.dto.CompanyInfo;
import com.smin.dto.Result;
import com.smin.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by phuonghqh on 2/15/15.
 */
@Controller
public class CompanyController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

//  @Resource
//  private SimpMessagingTemplate messagingTemplate;

//  @Value("${sudo.pwd}")
//  private String sudoPwd;

  @Value("${db.host}")
  private String dbHost;

  @Value("${db.username}")
  private String dbUsername;

  @Value("${db.password}")
  private String dbPassword;

  @Value("${db.driverClass}")
  private String dbDriverClass;

//  @Value("${smin.cmd.install}")
//  private String installCmd;

  @Resource
  private CompanyService companyService;

//  @Resource
//  private ApplicationContext applicationContext;

//  @ResponseBody
//  @RequestMapping(value = "/company", method = RequestMethod.GET)
//  public List<CompanyInfo> all() {
//    return companyService.findAll();
//  }

  @ResponseBody
  @RequestMapping(value = "/company/register", method = RequestMethod.POST)
  public Result register(@RequestBody CompanyInfo companyInfo) {
    companyService.register(companyInfo);
    return Result.Success;
//    SService service = applicationContext.getBean(companyInfo.getType() + "Service", SService.class);
//    return service.doService(companyInfo);
  }


//
//  @MessageMapping("/user/company/register")
//  public void register(CompanyInfo companyInfo) {
//    final String destination = "/topic/" + companyInfo.getInstallName() + "/company/register";
//    String command = String.format(installCmd, companyInfo.getInstallName(), companyInfo.getAdminPassword(), companyInfo.getPublicDomain());
//    try {
//      String[] cmd = {"sh", "-c", "echo '" + sudoPwd + "'| sudo -S " + command};
//      final Process process = Runtime.getRuntime().exec(cmd);
//      StringBuilder sysReportBuilder = new StringBuilder();
//      Thread[] threads = new Thread[]{
//        new InputStreamTask(process.getInputStream(), LOGGER, messagingTemplate, destination, sysReportBuilder),
//        new InputStreamTask(process.getErrorStream(), LOGGER, messagingTemplate, destination, sysReportBuilder)
//      };
//
//      for (Thread t : threads) {
//        t.start();
//      }
//
//      RunningTask warmTask = new RunningTask(">>System running tasks in the background...", LOGGER, messagingTemplate, destination);
//      warmTask.start();
//
//      for (Thread t : threads) {
//        t.join();
//      }
//
//      LOGGER.debug("All threads stopped");
//      warmTask.setStop(true);
//      companyInfo.setSysReport(sysReportBuilder.toString());
//      companyService.register(companyInfo);
//      messagingTemplate.convertAndSend(destination, "ENDED!!!!");
//    }
//    catch (Exception e) {
//      LOGGER.error("Error", e);
//      messagingTemplate.convertAndSend(destination, "Error, see log file for details");
//    }
//  }
}

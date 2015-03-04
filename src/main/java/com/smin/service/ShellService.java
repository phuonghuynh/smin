package com.smin.service;

import com.smin.task.StringStreamTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by phuonghqh on 3/4/15.
 */
@Service
public class ShellService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ShellService.class);

  @Value("${sudo.pwd}")
  private String sudoPwd;

  public void bash(String path) {
    try {
      String[] cmd = {"sh", "-c", "echo '" + sudoPwd + "'| sudo -S bash " + path};
      Process process = Runtime.getRuntime().exec(cmd);
      StringBuilder sysReportBuilder = new StringBuilder();
      Thread[] threads = new Thread[]{
        new StringStreamTask(process.getInputStream(), (output) -> {
        }),
        new StringStreamTask(process.getErrorStream(), (output) -> {
        })
      };
      for (Thread t : threads) {
        t.join();
      }
      LOGGER.debug("All threads stopped.");
    }
    catch (Exception e) {
      LOGGER.error("ERROR: ", e);
    }

  }
}

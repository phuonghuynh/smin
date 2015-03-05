package com.smin.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by phuonghqh on 3/4/15.
 */
//@Service
public class Bean {
  @Value("${orpm.soft}")
  public org.springframework.core.io.Resource resource;
}

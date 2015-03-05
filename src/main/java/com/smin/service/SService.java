package com.smin.service;

import com.smin.dto.CompanyInfo;
import com.smin.dto.Result;

/**
 * Created by phuonghqh on 3/3/15.
 */
public interface SService {
  Result doService(CompanyInfo companyInfo) throws Exception;
}

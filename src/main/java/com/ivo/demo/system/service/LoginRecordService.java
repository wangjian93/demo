package com.ivo.demo.system.service;

import com.ivo.demo.common.PageResult;
import com.ivo.demo.system.entity.LoginRecord;

/**
 * @author wangjian
 * @date 2018/9/3
 */
public interface LoginRecordService {

    boolean add(LoginRecord loginRecord);

    PageResult<LoginRecord> list(int pageNum, int pageSize, String startDate, String endDate, String account);

    Integer count();
}

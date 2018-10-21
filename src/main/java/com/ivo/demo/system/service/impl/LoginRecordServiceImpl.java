package com.ivo.demo.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ivo.demo.common.PageResult;
import com.ivo.demo.system.repository.LoginRecordMapper;
import com.ivo.demo.system.entity.LoginRecord;
import com.ivo.demo.system.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/9/3
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService {

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    @Override
    public boolean add(LoginRecord loginRecord) {
        loginRecord.setCreateTime(new Date());
        return loginRecordMapper.insert(loginRecord) > 0;
    }

    @Override
    public PageResult<LoginRecord> list(int pageNum, int pageSize, String startDate, String endDate, String account) {
        Page<LoginRecord> page = new Page<>(pageNum, pageSize);
        List<LoginRecord> records = loginRecordMapper.listFull(page, startDate, endDate, account);
        return new PageResult<>(page.getTotal(), records);
    }

    @Override
    public Integer count() {
        Wrapper<LoginRecord> wrapper = new EntityWrapper<>();
        return loginRecordMapper.selectCount(wrapper);
    }
}

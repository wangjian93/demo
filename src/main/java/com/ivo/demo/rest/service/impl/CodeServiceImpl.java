package com.ivo.demo.rest.service.impl;

import com.ivo.demo.rest.entity.Code;
import com.ivo.demo.rest.repository.CodeMapper;
import com.ivo.demo.rest.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/15
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Code> searchAll() {
        return codeMapper.searchAll();
    }
}

package com.ivo.demo.rest.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ivo.demo.rest.entity.Code;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/15
 */
@DS("slave")
public interface CodeMapper {
    List<Code> searchAll();
}

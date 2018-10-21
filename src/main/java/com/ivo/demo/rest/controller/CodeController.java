package com.ivo.demo.rest.controller;

import com.ivo.demo.common.JsonResult;
import com.ivo.demo.rest.entity.Code;
import com.ivo.demo.rest.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/15
 */
@Controller
@RequestMapping("/rest/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping
    @ResponseBody
    public JsonResult code() {
        List<Code> codeList = codeService.searchAll();

        return JsonResult.ok().put("data", codeList);
    }
}

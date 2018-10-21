package com.ivo.demo;

import com.ivo.demo.rest.service.CodeService;
import com.ivo.demo.system.service.LoginRecordService;
import com.ivo.demo.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService userService ;

    @Autowired
    private CodeService codeService;

    @Autowired
    private LoginRecordService loginRecordService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        loginRecordService.list(1,10, null, null, null);
    }

    @Test
    public void test2() {
        System.out.println(codeService.searchAll().size());
    }

    @Test
    public void test3() {
        userService.getById(1);
    }


}

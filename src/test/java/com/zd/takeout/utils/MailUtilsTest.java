package com.zd.takeout.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailUtilsTest {

    @Autowired
    MailUtils mailUtils;
    @Test
    public void testSimpleMsg() {
        String to = "417256279@qq.com";
        String subject = "测试";
        String content = "你接受到了吗";
        mailUtils.sendSimpleMsg(to,subject,content);
    }

}
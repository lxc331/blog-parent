package com.example.blog;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestProject {
    private static final String slat = "example!@#";
    //测试密码md5加密
    //123456 + example!@# = 123456example!@#
    @Test
    public void test1() {
        String password= "123456";
        String res = DigestUtils.md5Hex(password + slat);
        System.out.println(res);
        log.info(res);
    }


}

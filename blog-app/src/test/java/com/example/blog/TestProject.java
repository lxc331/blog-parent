package com.example.blog;


import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class TestProject {
    private static final String slat = "example!@#";
    @Test
    public void test1() {
        String password= "123456";
        String res = DigestUtils.md5Hex(password + slat);
        System.out.println(res);
    }


}

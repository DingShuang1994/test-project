package com.douniwan;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    public static String encode(String word){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode(word);
        return pwd;
    }

    public static void main(String[] args) {
        String encode = encode("123");
        System.out.println(encode);
    }
}

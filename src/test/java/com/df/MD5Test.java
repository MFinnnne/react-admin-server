package com.df;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2021/2/11 0:15
 */
public class MD5Test {

    @Test
    public void test() throws NoSuchAlgorithmException {
        String pw = "12122321";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(pw.getBytes());
        System.out.println(digest);
    }
}
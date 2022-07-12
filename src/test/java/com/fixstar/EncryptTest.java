package com.fixstar;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

public class EncryptTest {

    @Test
    public void jasypt(){
        String str = "DRIVING_RECORD";
        String encrypt = jasyptEncrypt(str);
        System.out.println("encrypt : " + encrypt);
        Assertions.assertThat(str).isEqualTo(jasyptDecryt(encrypt));
    }

    private String jasyptEncrypt(String input) {
        String key = "00";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private String jasyptDecryt(String input){
        String key = "00";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }
}
